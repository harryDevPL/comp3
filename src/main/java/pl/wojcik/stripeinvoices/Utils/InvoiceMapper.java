package pl.wojcik.stripeinvoices.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.wojcik.stripeinvoices.model.InvoiceRequest;
import pl.wojcik.stripeinvoices.model.InvoiceStatus;
import pl.wojcik.stripeinvoices.model.dto.InvoiceDTO;
import pl.wojcik.stripeinvoices.model.entity.Invoice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceMapper {

    public static InvoiceDTO convertEntityToDTO(Invoice invoice) {
        return InvoiceDTO.builder()
                .dtoDBId(invoice.getId())
                .id(invoice.getInvoice_id())
                .customer(invoice.getCustomer())
                .customerEmail(invoice.getCustomer_email())
                .autoAdvance(invoice.isAuto_advance())
                .collectionMethod(invoice.getCollection_method())
                .description(invoice.getDesc())
                .build();
    }

    public static Invoice convertRequestToEntity(InvoiceRequest request) {
        return Invoice.builder()
                .invoice_id(InvoiceIdGenerator.generate())
                .customer(request.getCustomer())
                .auto_advance(request.isAutoAdvance())
                .collection_method(request.getCollectionMethod())
                .desc(request.getDescription())
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
