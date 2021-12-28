package fastfoodbackend.fastfoodbackend.Repository;

import fastfoodbackend.fastfoodbackend.Models.Company;
import fastfoodbackend.fastfoodbackend.Models.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, String>, JpaSpecificationExecutor<InvoiceItem> {
}
