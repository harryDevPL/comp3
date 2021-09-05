package pl.wojcik.stripeinvoices.model;

import lombok.Data;
import pl.wojcik.stripeinvoices.model.entity.Product;

import java.sql.Timestamp;
import java.util.List;

@Data
public class InvoiceRequest {

    private String customer;
    private boolean autoAdvance;
    private String collectionMethod;
    private String description;

    private String charge;
    private String currency;
    private List<Product> lines;
    private Timestamp periodStart;
    private Timestamp periodEnd;
    private String status;
    private Integer total;
    private String accountCountry;
    private String accountName;
    private Integer amountDue;
    private Integer amountPaid;
    private Integer amountRemaining;
    private Integer attemptCount;
    private boolean attempted;
    private Timestamp created;
    private String customerEmail;
    private String customerName;
}
