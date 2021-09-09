package pl.wojcik.stripeinvoices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.wojcik.stripeinvoices.database.MySQLTestDatabase;

@SpringBootTest
@ContextConfiguration(initializers = {MySQLTestDatabase.Initializer.class})
class StripeInvoicesApplicationTests {

	@Test
	void contextLoads() {
	}

}
