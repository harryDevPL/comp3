package pl.wojcik.stripeinvoices.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceRequestValidator {

    public static boolean validate(InvoiceRequest request) {
        return request != null &&
                EmailValidator.validate(request.getCustomerEmail()) &&
                request.getCustomer() != null &&
                !request.getCustomer().isBlank();
    }
}
