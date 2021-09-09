package pl.wojcik.stripeinvoices.service;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import pl.wojcik.stripeinvoices.bootstrap.TestData;
import pl.wojcik.stripeinvoices.database.MySQLTestDatabase;
import pl.wojcik.stripeinvoices.exceptions.ApiRequestException;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.model.entity.Invoice;
import pl.wojcik.stripeinvoices.repository.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@SpringBootTest()
@ContextConfiguration(initializers = {MySQLTestDatabase.Initializer.class})
class InvoiceServiceTest {

    @MockBean
    private InvoiceRepository repository;

    @Autowired
    private InvoiceService service;

    @Test
    void shouldThrowException_whenGetInvoiceIdIsNull() {
        assertThrows(ApiRequestException.class, () -> service.getInvoiceById(null));
    }

    @Test
    void shouldThrowException_whenGetInvoiceIdIsTooShort() {
        assertThrows(ApiRequestException.class, () -> service.getInvoiceById("null"));
    }

    @Test
    void shouldReturnInvoiceDTO_whenGetInvoiceIdIsValid() {
        String id = UUID.randomUUID().toString().replace("-", "");

        Mockito.when(repository.findInvoiceByInvoiceId(id)).thenReturn(Optional.of(TestData.INVOICE_1));
        service.getInvoiceById(id);

        InOrder order = Mockito.inOrder(repository);
        order.verify(repository, times(1)).findInvoiceByInvoiceId(id);
    }

    @Test
    void shouldReturnNoContentStatus_whenGetInvoiceIdIsValidButNoInDB() {
        String id = UUID.randomUUID().toString().replace("-", "");

        Mockito.when(repository.findInvoiceByInvoiceId(id)).thenReturn(Optional.empty());
        ResponseEntity<InvoiceDTO> invoiceById = service.getInvoiceById(id);
        HttpStatus responseStatusCode = invoiceById.getStatusCode();

        InOrder order = Mockito.inOrder(repository);
        order.verify(repository, times(1)).findInvoiceByInvoiceId(id);
        assertEquals(HttpStatus.NO_CONTENT, responseStatusCode);
    }

    @Test
    void shouldReturnInvoiceFromDB_whenGetInvoiceIdIsValid() {
        String id = UUID.randomUUID().toString().replace("-", "");
        Invoice invoice = TestData.INVOICE_1;
        invoice.setInvoice_id(id);

        Mockito.when(repository.findInvoiceByInvoiceId(id)).thenReturn(Optional.of(invoice));
        ResponseEntity<InvoiceDTO> invoiceById = service.getInvoiceById(id);
        HttpStatus responseStatusCode = invoiceById.getStatusCode();

        InOrder order = Mockito.inOrder(repository);
        order.verify(repository, times(1)).findInvoiceByInvoiceId(id);
        assertEquals(HttpStatus.OK, responseStatusCode);
    }

    @Test
    void shouldReturnInvoiceListFromDB_whenDBIsNotEmpty() {
        Mockito.when(repository.findAll()).thenReturn(List.of(TestData.INVOICE_1, TestData.INVOICE_2));
        ResponseEntity<List<InvoiceDTO>> allInvoices = service.getAllInvoices();
        HttpStatus responseStatusCode = allInvoices.getStatusCode();

        InOrder order = Mockito.inOrder(repository);
        order.verify(repository, times(1)).findAll();
        assertEquals(HttpStatus.OK, responseStatusCode);
        assertEquals(2, allInvoices.getBody().size());
    }

    @Test
    void shouldReturnEmptyInvoiceListFromDB_whenDBIsEmpty() {
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<InvoiceDTO>> allInvoices = service.getAllInvoices();
        HttpStatus responseStatusCode = allInvoices.getStatusCode();

        InOrder order = Mockito.inOrder(repository);
        order.verify(repository, times(1)).findAll();
        assertEquals(HttpStatus.NO_CONTENT, responseStatusCode);
    }

    @Test
    void shouldCreateInvoiceInDBFromRequest_whenInvoiceRequestIsValid() {
        InvoiceRequest request = TestData.REQUEST_1;

        ResponseEntity<HttpStatus> newInvoice = service.createNewInvoice(request);
        HttpStatus responseStatusCode = newInvoice.getStatusCode();

        assertEquals(HttpStatus.CREATED, responseStatusCode);
    }

    @Test
    void shouldThrowException_whenInvoiceRequestIsInvalid() {
        InvoiceRequest request = TestData.REQUEST_INVALID_1;

        assertThrows(NullPointerException.class, () -> {
            service.createNewInvoice(request);
        });
    }
}