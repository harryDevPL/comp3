package pl.wojcik.stripeinvoices.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceRequestValidator {

    private static final String CHARGE_AUTOMATICALLY = "charge_automatically";
    private static final String SEND_INVOICE = "send_invoice";

    // here can be precise logic how to validate a request
    // e.g. validation for the client data, products data, payment etc.
    // in real app it should be more realistic and way complex :)
    public static boolean validate(InvoiceRequest request) {
        return request != null && EmailValidator.validate(request.getCustomerEmail()) &&
                request.getCustomer() != null && checkCollectionMethod(request.getCollectionMethod());
    }

    private static boolean checkCollectionMethod(String collectionMethod) {
        return collectionMethod.equals(CHARGE_AUTOMATICALLY) || collectionMethod.equals(SEND_INVOICE);
    }
}
