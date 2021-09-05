package pl.wojcik.stripeinvoices.model.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import pl.wojcik.stripeinvoices.model.InvoiceStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
public class Invoice {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String customer;

    private boolean auto_advance;
    private String collection_method;
    private String description;
    private String charge;
    private String currency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> lines;

    @Enumerated
    private InvoiceStatus status;

    private Integer total;
    private String account_country;
    private String account_name;
    private Integer amount_due;
    private Integer amount_paid;
    private Integer amount_remaining;
    private Integer attempt_count;
    private boolean attempted;
    private String customer_email;
    private String customer_name;

    // and many many more
}
