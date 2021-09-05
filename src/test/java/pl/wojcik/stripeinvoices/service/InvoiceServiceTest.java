package pl.wojcik.stripeinvoices.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.wojcik.stripeinvoices.database.PostgresTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ContextConfiguration(initializers = {PostgresTestDatabase.Initializer.class})
class InvoiceServiceTest {

}