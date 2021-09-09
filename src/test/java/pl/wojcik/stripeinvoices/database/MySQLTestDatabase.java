package pl.wojcik.stripeinvoices.database;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

public class MySQLTestDatabase {
    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static MySQLContainer<?> MySQL_CONTAINER = new MySQLContainer<>("mysql:latest");

        static {
            MySQL_CONTAINER.start();
        }

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + MySQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.username=" + MySQL_CONTAINER.getUsername(),
                    "spring.datasource.password=" + MySQL_CONTAINER.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
