package pl.wojcik.stripeinvoices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojcik.stripeinvoices.bootstrap.TestData;
import pl.wojcik.stripeinvoices.database.MySQLTestDatabase;
import pl.wojcik.stripeinvoices.exceptions.ApiRequestException;
import pl.wojcik.stripeinvoices.exceptions.ExceptionConstants;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.service.InvoiceService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = InvoicesController.class)
@ContextConfiguration(initializers = {MySQLTestDatabase.Initializer.class})
@AutoConfigureMockMvc
class InvoicesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InvoiceService invoiceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveRequestToDBWithStatusOk_whenInvoiceRequestIsValid() throws Exception {
        final InvoiceRequest invoiceRequest = TestData.REQUEST_1;
        final String requestAsString = objectMapper.writeValueAsString(invoiceRequest);
        Mockito.when(invoiceService.createNewInvoice(invoiceRequest)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        mockMvc.perform(post(TestData.CREATE_INVOICE_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void shouldThrowException_whenInvoiceRequestIsInvalid() throws Exception {
        final InvoiceRequest invoiceRequest = TestData.REQUEST_INVALID_1;
        final String requestAsString = objectMapper.writeValueAsString(invoiceRequest);
        Mockito.when(invoiceService.createNewInvoice(invoiceRequest)).thenThrow(new ApiRequestException(ExceptionConstants.BAD_REQUEST_INVALID_INVOICE));

        mockMvc.perform(post(TestData.CREATE_INVOICE_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void shouldReturnListOfAllInvoices_whenGetRequestIsSend() throws Exception {
        List<InvoiceDTO> invoiceList = Arrays.asList(TestData.INVOICE_DTO_1, TestData.INVOICE_DTO_2, TestData.INVOICE_DTO_3);
        Mockito.when(invoiceService.getAllInvoices()).thenReturn(new ResponseEntity<>(invoiceList, HttpStatus.OK));

        mockMvc.perform(get(TestData.GET_ALL_INVOICES_PATH))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldReturnNoContentStatus_whenGetRequestIsSendAndNoContentIsInDB() throws Exception {
        Mockito.when(invoiceService.getAllInvoices()).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        mockMvc.perform(get(TestData.GET_ALL_INVOICES_PATH))
                .andDo(log())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void shouldReturnInvoiceOfGivenId_whenGetRequestIsSendAndNoContentIsInDB() throws Exception {
        String id = "id_3_sadfsdaasgsa";
        Mockito.when(invoiceService.getInvoiceById(id)).thenReturn(new ResponseEntity<>(TestData.INVOICE_DTO_3, HttpStatus.OK));

        mockMvc.perform(get(TestData.GET_INVOICE_BY_ID_PATH + "/" + id))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andReturn();

        verify(invoiceService, times(1)).getInvoiceById(any(String.class));
    }
}