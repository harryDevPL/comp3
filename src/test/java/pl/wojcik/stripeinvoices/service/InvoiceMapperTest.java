package pl.wojcik.stripeinvoices.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wojcik.stripeinvoices.Utils.InvoiceMapper;
import pl.wojcik.stripeinvoices.bootstrap.TestData;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.model.entity.Invoice;

class InvoiceMapperTest {

    @Test
    void shouldConvertEntityToDto_whenEntityIsValid() {
        // GIVEN
        Invoice invoice = TestData.INVOICE_1;
        // WHEN
        InvoiceDTO invoiceDTO = InvoiceMapper.convertEntityToDTO(invoice);
        // THEN
        Assertions.assertEquals(invoice.getCustomer(), invoiceDTO.getCustomer());
        Assertions.assertEquals(invoice.getCustomer_email(), invoiceDTO.getCustomerEmail());
        Assertions.assertEquals(invoice.getAmount_paid(), invoiceDTO.getAmountPaid());
    }

    @Test
    void shouldConvertRequestToEntity_whenRequestIsValid() {
        // GIVEN
        InvoiceRequest invoiceRequest = TestData.REQUEST_1;
        // WHEN
        Invoice invoice = InvoiceMapper.convertRequestToEntity(invoiceRequest);
        // THEN
        Assertions.assertEquals(invoice.getCustomer(), invoiceRequest.getCustomer());
        Assertions.assertEquals(invoice.getCustomer_email(), invoiceRequest.getCustomerEmail());
        Assertions.assertEquals(invoice.getCollection_method(), invoiceRequest.getCollectionMethod());
    }

    @Test
    void shouldThrowNullPointerException_whenRequestStatusIsNull() {
        // GIVEN
        InvoiceRequest invoiceRequest = TestData.REQUEST_1;
        invoiceRequest.setStatus(null);
        // WHEN, THEN
        Assertions.assertThrows(NullPointerException.class, () -> {
            InvoiceMapper.convertRequestToEntity(invoiceRequest);
        });
    }

    @Test
    void shouldThrowNullPointerException_whenRequestIsNull() {
        // GIVEN
        InvoiceRequest invoiceRequest = null;
        // WHEN, THEN
        Assertions.assertThrows(NullPointerException.class, () -> {
            InvoiceMapper.convertRequestToEntity(invoiceRequest);
        });
    }
}