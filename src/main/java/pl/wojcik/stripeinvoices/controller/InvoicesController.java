package pl.wojcik.stripeinvoices.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
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
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class InvoicesController {

    private static final Logger logger = LoggerFactory.getLogger(InvoicesController.class);
    private final InvoiceService invoiceService;

    @ApiOperation(value = "Find single invoice by invoice id (string, not by database id!).",
            response = InvoiceDTO.class,
            httpMethod = "GET",
            notes = "Id is generated by application during persisting to sql database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "NO CONTENT!"),
            @ApiResponse(code = 400, message = "BAD REQUEST!")
    })
    @GetMapping(path = "/invoice/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceDTO> getInvoicesById(@PathVariable(name = "id", required = false) String id) {
        logger.info("Sending request -> service layer -> invoice of id: {0}", id);
        return invoiceService.getInvoiceById(id);
    }
    // swagger config can be like above or extended
    @GetMapping(path = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        logger.info("Sending request -> service layer -> all invoices.");
        return invoiceService.getAllInvoices();
    }

    // swagger config can be like above or extended
    @PostMapping(path = "/invoice", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createInvoice(@RequestBody InvoiceRequest request) {
        logger.info("Sending request -> service layer -> creation fo invoice.");
        return invoiceService.createNewInvoice(request);
    }
}
