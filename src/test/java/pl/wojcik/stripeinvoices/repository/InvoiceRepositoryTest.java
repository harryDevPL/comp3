package pl.wojcik.stripeinvoices.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import pl.wojcik.stripeinvoices.bootstrap.TestData;
import pl.wojcik.stripeinvoices.database.MySQLTestDatabase;
import pl.wojcik.stripeinvoices.model.entity.Invoice;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = {MySQLTestDatabase.Initializer.class})
class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveInvoiceToDB_whenInvoiceIsNotNull() {
        Invoice expectedInvoice = TestData.INVOICE_1;
        Invoice actualInvoice = repository.save(expectedInvoice);
        assertEquals(expectedInvoice.getCustomer_email(), actualInvoice.getCustomer_email());
    }

    @Test
    void shouldReturnAllInvoice_whenDBIsNotEmpty() {
        Invoice invoice1 = TestData.INVOICE_1;
        Invoice invoice2 = TestData.INVOICE_2;
        Invoice invoice3 = TestData.INVOICE_3;
        repository.save(invoice1);
        repository.save(invoice2);
        repository.save(invoice3);

        List<Invoice> invoiceList = repository.findAll();
        assertEquals(3, invoiceList.size());
        assertFalse(invoiceList.isEmpty());
    }

    @Test
    void shouldReturnEmptyList_whenDBIsEmpty() {
        List<Invoice> invoiceList = repository.findAll();
        assertTrue(invoiceList.isEmpty());
    }
}