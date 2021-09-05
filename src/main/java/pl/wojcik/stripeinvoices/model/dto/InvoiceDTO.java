package pl.wojcik.stripeinvoices.model.dto;

import lombok.Builder;
import lombok.Data;
import pl.wojcik.stripeinvoices.model.InvoiceStatus;
import pl.wojcik.stripeinvoices.model.entity.Product;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class InvoiceDTO {

    private String dto_id;

    private String customer;
    private boolean autoAdvance;
    private String collectionMethod;
    private String description;
    private String charge;
    private String currency;
    private List<Product> lines;
    private InvoiceStatus status;
    private Integer total;
    private String accountCountry;
    private String accountName;
    private Integer amountDue;
    private Integer amountPaid;
    private Integer amountRemaining;
    private Integer attemptCount;
    private boolean attempted;
    private String customerEmail;
    private String customerName;
}
