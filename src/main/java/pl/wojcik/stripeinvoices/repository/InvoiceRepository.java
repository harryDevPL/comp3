package pl.wojcik.stripeinvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wojcik.stripeinvoices.model.entity.Invoice;

import java.util.Optional;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * form Invoice i WHERE i.id = ?1", nativeQuery = true)
    Optional<Invoice> findInvoiceById(String id);
}
