package pl.wojcik.stripeinvoices.database;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestDatabase {
    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static PostgreSQLContainer<?> postgresqlTestContainer = new PostgreSQLContainer<>("postgres:latest");

        static {
            postgresqlTestContainer.start();
        }

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlTestContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresqlTestContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlTestContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }}
