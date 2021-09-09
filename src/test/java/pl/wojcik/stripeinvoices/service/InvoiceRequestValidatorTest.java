package pl.wojcik.stripeinvoices.service;

import org.junit.jupiter.api.Test;
import pl.wojcik.stripeinvoices.Utils.InvoiceRequestValidator;
import pl.wojcik.stripeinvoices.bootstrap.TestData;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvoiceRequestValidatorTest {

    @Test
    void shouldReturnTrue_whenRequestIsNotValid() {
        // GIVEN
        InvoiceRequest request = TestData.REQUEST_1;
        // WHEN
        boolean validate = InvoiceRequestValidator.validate(request);
        // THEN
        assertThat(validate).isFalse();
    }

    @Test
    void shouldReturnTrue_whenRequestIsValid() {
        // GIVEN
        InvoiceRequest request = InvoiceRequest.builder()
                .customer("Customer")
                .customerEmail("customer@gmail.com")
                .collectionMethod("charge_automatically")
                .build();
        // WHEN
        boolean validate = InvoiceRequestValidator.validate(request);
        // THEN
        assertThat(validate).isTrue();
    }

    @Test
    void shouldThrowNullPointerException_whenRequestCustomerEmailIsNull() {
        // GIVEN
        InvoiceRequest request = InvoiceRequest.builder()
                .customer("customer")
                .customerEmail(null)
                .collectionMethod("charge_automatically")
                .build();
        // WHEN, THEN
        assertThrows(NullPointerException.class, () -> {
            InvoiceRequestValidator.validate(request);
        });
    }

    @Test
    void shouldReturnFalse_whenRequestCollectionMethodIsNotValid() {
        // GIVEN
        InvoiceRequest request = InvoiceRequest.builder()
                .customer("customer")
                .customerEmail("null@gmail.com")
                .collectionMethod("charge_automatically222")
                .build();
        // WHEN
        boolean validate = InvoiceRequestValidator.validate(request);
        // THEN
        assertThat(validate).isFalse();
    }

    @Test
    void shouldReturnFalse_whenRequestCustomerIsNull() {
        // GIVEN
        InvoiceRequest request = InvoiceRequest.builder()
                .customer(null)
                .customerEmail("null@gmail.com")
                .collectionMethod("charge_automatically222")
                .build();
        // WHEN
        boolean validate = InvoiceRequestValidator.validate(request);
        // THEN
        assertThat(validate).isFalse();
    }

}