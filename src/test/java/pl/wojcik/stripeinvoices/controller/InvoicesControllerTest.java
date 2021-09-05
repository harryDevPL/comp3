package pl.wojcik.stripeinvoices.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import pl.wojcik.stripeinvoices.database.PostgresTestDatabase;

@WebMvcTest(controllers = InvoicesController.class)
@ContextConfiguration(initializers = {PostgresTestDatabase.Initializer.class})
class InvoicesControllerTest {


}