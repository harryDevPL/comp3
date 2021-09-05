package pl.wojcik.stripeinvoices.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.InvoiceStatus;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.model.entity.Invoice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceMapper {

    protected static InvoiceDTO convertEntityToDTO(Invoice invoice) {
        return InvoiceDTO.builder()
                .dto_id(invoice.getId())
                .customer(invoice.getCustomer())
                .autoAdvance(invoice.isAuto_advance())
                .collectionMethod(invoice.getCollection_method())
                .description(invoice.getDescription())
                .build();
    }

    public static Invoice requestToEntity(InvoiceRequest request) {
        return Invoice.builder()
                .customer(request.getCustomer())
                .auto_advance(request.isAutoAdvance())
                .collection_method(request.getCollectionMethod())
                .description(request.getDescription())
                .charge(request.getCharge())
                .currency(request.getCurrency())
                .lines(request.getLines())
                .status(InvoiceStatus.valueOf(request.getStatus()))
                .total(request.getTotal())
                .account_country(request.getAccountCountry())
                .account_name(request.getAccountName())
                .amount_due(request.getAmountDue())
                .amount_paid(request.getAmountPaid())
                .amount_remaining(request.getAmountRemaining())
                .attempt_count(request.getAttemptCount())
                .attempted(request.isAttempted())
                .customer_email(request.getCustomerEmail())
                .customer_name(request.getCustomerName())
                .build();
    }
}
