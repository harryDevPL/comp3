package pl.wojcik.stripeinvoices.bootstrap;

import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.model.entity.Invoice;
import pl.wojcik.stripeinvoices.Utils.InvoiceIdGenerator;

public interface TestData {

    Invoice INVOICE_1 = Invoice.builder()
            .invoice_id("InvoiceIdGenerator.generate()")
            .auto_advance(false)
            .customer_name(TestData.CUSTOMER_1)
            .customer("Customer_one")
            .customer_email(TestData.CUSTOMER_EMAIL_1)
            .total(TestData.TOTAL_1)
            .amount_remaining(TestData.REMAINING_1)
            .amount_paid(TestData.PAID_1)
            .account_country(TestData.ACCOUNT_COUNTRY_1)
            .collection_method(TestData.COLLECTION_METHOD_1)
            .attempted(TestData.ATTEMPTED_1)
            .build();

    Invoice INVOICE_2 = Invoice.builder()
            .invoice_id(InvoiceIdGenerator.generate())
            .auto_advance(false)
            .customer_name("Customer_2")
            .customer("Customer_two")
            .customer_email("customer2@gmail.com")
            .total(299)
            .amount_remaining(200)
            .amount_paid(99)
            .account_country("pol")
            .collection_method("charge_automatically")
            .attempted(true)
            .build();

    Invoice INVOICE_3 = Invoice.builder()
            .invoice_id(InvoiceIdGenerator.generate())
            .auto_advance(true)
            .customer_name("Customer_2")
            .customer("Customer_three")
            .customer_email("customer3@gmail.com")
            .total(599)
            .amount_remaining(500)
            .amount_paid(99)
            .account_country("usa")
            .collection_method("send_invoice")
            .attempted(true)
            .build();

    String CUSTOMER_1 = "Customer_1";
    String CUSTOMER_EMAIL_1 = "customer1@gmail.com";
    boolean ATTEMPTED_1 = false;
    Integer TOTAL_1 = 299;
    Integer REMAINING_1 = 299;
    Integer PAID_1 = 0;
    String ACCOUNT_COUNTRY_1 = "pol";
    String COLLECTION_METHOD_1 = "charge_automatically";

    String CREATE_INVOICE_PATH = "http://localhost:8080/api/v1/invoice";
    String GET_INVOICE_BY_ID_PATH = "http://localhost:8080/api/v1/invoice/";
    String GET_ALL_INVOICES_PATH = "http://localhost:8080/api/v1/invoices";

    String REQUEST_AUTHORIZATION_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjEzMzg5NzE4fQ.CYXsOJlJPlTh425m0kg9Ewd-Ih6mFmv_FGCCPxCW51k";
    String LOGIN_CREDENTIALS_EXAMPLE_TO_STRING = "{ \"username\" : \"test\", \"password\" : \"test\" }";

    InvoiceRequest REQUEST_1 = InvoiceRequest.builder()
            .autoAdvance(true)
            .customer("Customer_1_2")
            .customerName("Customer_2")
            .customerEmail("customer3@gmail.com")
            .total(599)
            .status("DRAFT")
            .amountRemaining(500)
            .amountPaid(99)
            .accountCountry("usa")
            .collectionMethod(TestData.COLLECTION_METHOD_1)
            .attempted(true)
            .build();
    InvoiceRequest REQUEST_INVALID_1 = InvoiceRequest.builder()
            .total(599)
            .status("DRAFT")
            .amountRemaining(700)
            .amountPaid(-10)
            .accountCountry("usa")
            .attempted(true)
            .build();

    InvoiceDTO INVOICE_DTO_1 = InvoiceDTO.builder()
            .id("id_1_sadfsdaasgsa")
            .customer("customer_1")
            .customerEmail("customer_1@email.com")
            .collectionMethod(COLLECTION_METHOD_1)
            .build();

    InvoiceDTO INVOICE_DTO_2 = InvoiceDTO.builder()
            .id("id_2_sadfsdaasgsa")
            .customer("customer_2")
            .customerEmail("customer_2@email.com")
            .collectionMethod(COLLECTION_METHOD_1)
            .build();

    InvoiceDTO INVOICE_DTO_3 = InvoiceDTO.builder()
            .id("id_3_sadfsdaasgsa")
            .customer("customer_3")
            .customerEmail("customer_3@email.com")
            .collectionMethod(COLLECTION_METHOD_1)
            .build();
}
