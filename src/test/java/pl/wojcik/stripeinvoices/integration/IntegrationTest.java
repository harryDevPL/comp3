package pl.wojcik.stripeinvoices.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.wojcik.stripeinvoices.bootstrap.TestData;
import pl.wojcik.stripeinvoices.database.MySQLTestDatabase;
import pl.wojcik.stripeinvoices.repository.InvoiceRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(initializers = {MySQLTestDatabase.Initializer.class})
class IntegrationTest {

    protected MockMvc mockMvc;

    @Autowired
    InvoiceRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        repository.save(TestData.INVOICE_1);
        repository.save(TestData.INVOICE_2);
        repository.save(TestData.INVOICE_3);
    }

    @Test
    void shouldCreateInvoiceInDB_whenRequestIsValid() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get(TestData.GET_INVOICE_BY_ID_PATH + "/" + "InvoiceIdGenerator.generate()")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();

        String responseAsString = mvcResult.getResponse().getContentAsString();
        assertThat(responseAsString).contains("InvoiceIdGenerator.generate()");
        assertThat(responseAsString).contains("Customer_one");
        assertThat(responseAsString).contains("customer1@gmail.com");
        assertThat(responseAsString).contains("charge_automatically");
        // I know, it should be checked in more efficient way, not stringA contains string B
    }
}
