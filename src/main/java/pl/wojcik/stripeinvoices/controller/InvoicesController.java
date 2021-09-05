package pl.wojcik.stripeinvoices.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class InvoicesController {

    private final Logger logger = LoggerFactory.getLogger(InvoicesController.class);
    private final InvoiceService invoiceService;


    @GetMapping(path = "/invoice/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<InvoiceDTO> getInvoicesById(@PathVariable(name = "id", required = false) String id) {
        logger.info("Sending request -> service layer -> invoice of id: {0}", id);
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping(path = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        logger.info("Sending request -> service layer -> all invoices.");
        return invoiceService.getAllInvoices();
    }

    @PostMapping(path = "/invoice", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createInvoice(@RequestBody InvoiceRequest request) {
        logger.info("Sending request -> service layer -> creation fo invoice.");
        return invoiceService.createNewInvoice(request);
    }
}
