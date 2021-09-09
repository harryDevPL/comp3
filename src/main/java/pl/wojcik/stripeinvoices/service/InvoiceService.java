package pl.wojcik.stripeinvoices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wojcik.stripeinvoices.Utils.InvoiceMapper;
import pl.wojcik.stripeinvoices.Utils.InvoiceRequestValidator;
import pl.wojcik.stripeinvoices.exceptions.ApiRequestException;
import pl.wojcik.stripeinvoices.exceptions.ExceptionConstants;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.model.entity.Invoice;
import pl.wojcik.stripeinvoices.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Cacheable(cacheNames = "SingleInvoice", key = "#id")
    public ResponseEntity<InvoiceDTO> getInvoiceById(String id) {
        if (validateId(id)) {
            logger.warn("User send bad request. Invoice Id was: {0}", id);
            throw new ApiRequestException(ExceptionConstants.BAD_REQUEST_WRONG_ID);
        }
        Optional<Invoice> invoiceById = repository.findInvoiceByInvoiceId(id);
        if (invoiceById.isPresent()) {
            InvoiceDTO invoiceDTO = InvoiceMapper.convertEntityToDTO(invoiceById.get());
            return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @Cacheable(cacheNames = "AllInvoices")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<Invoice> invoiceList = repository.findAll();
        if (invoiceList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<InvoiceDTO> invoiceDTOList = invoiceList.stream()
                    .map(InvoiceMapper::convertEntityToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(invoiceDTOList, HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> createNewInvoice(InvoiceRequest request) {
        if (!InvoiceRequestValidator.validate(request)) {
            throw new ApiRequestException(ExceptionConstants.BAD_REQUEST_INVALID_INVOICE);
        } else {
            repository.save(InvoiceMapper.convertRequestToEntity(request));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    private boolean validateId(String id) {
        // I assume that id length should be at least 10
        return id == null || id.length() <= 10;
    }
}
