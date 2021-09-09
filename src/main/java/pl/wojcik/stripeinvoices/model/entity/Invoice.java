package pl.wojcik.stripeinvoices.model.entity;

import lombok.*;
import pl.wojcik.stripeinvoices.model.InvoiceStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String invoice_id;

    @Column(nullable = false)
    private String customer;

    private boolean auto_advance;
    private String collection_method;
    private String invoice_description;
    private String charge;
    private String currency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk", referencedColumnName = "id")
    private Set<Product> lines;

    @Enumerated(EnumType.STRING)
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
