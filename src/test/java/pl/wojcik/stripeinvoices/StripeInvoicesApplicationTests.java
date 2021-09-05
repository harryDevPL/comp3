package pl.wojcik.stripeinvoices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.wojcik.stripeinvoices.database.PostgresTestDatabase;

@SpringBootTest
@ContextConfiguration(initializers = {PostgresTestDatabase.Initializer.class})
class StripeInvoicesApplicationTests {

	@Test
	void contextLoads() {
	}

}
