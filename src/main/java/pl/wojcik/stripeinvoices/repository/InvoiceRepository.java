package pl.wojcik.stripeinvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wojcik.stripeinvoices.model.entity.Invoice;

import java.util.Optional;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT i from Invoice i WHERE i.invoice_id = :id")
    Optional<Invoice> findInvoiceByInvoiceId(@Param("id") String id);
}
