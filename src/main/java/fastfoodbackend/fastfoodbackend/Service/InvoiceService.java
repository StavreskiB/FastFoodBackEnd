package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.Invoice;
import fastfoodbackend.fastfoodbackend.Models.InvoiceItem;
import fastfoodbackend.fastfoodbackend.Models.Norms;
import fastfoodbackend.fastfoodbackend.Models.Product;
import fastfoodbackend.fastfoodbackend.Repository.InvoiceItemRepository;
import fastfoodbackend.fastfoodbackend.Repository.InvoiceRepository;
import fastfoodbackend.fastfoodbackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Invoice saveNewInvoice (Invoice invoice){
        return invoiceRepository.saveAndFlush(invoice);
    }

    public List<Invoice> getAllInvoice() {return invoiceRepository.findAll();}

    public static Specification<Invoice> getInvoiceById(Integer invoiceId) {
        return (Specification<Invoice>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (invoiceId > 0 || invoiceId != null) {
                predicates.add(cb.equal(root.get("Name"), invoiceId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<InvoiceItem> getInvoiceItemsById(Integer invoiceId) {
        return (Specification<InvoiceItem>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (invoiceId > 0 || invoiceId != null) {
                predicates.add(cb.equal(root.get("IdInvoice"), invoiceId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Invoice> getInvByDate(Integer companyId) {
        return (Specification<Invoice>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }



    public List<InvoiceItem> getInvItemBySpec (Specification<InvoiceItem> spec){
        return invoiceItemRepository.findAll(spec);
    }


    public List<Invoice> getInvBySpec (Specification<Invoice> spec){
        return invoiceRepository.findAll(spec);
    }

    public InvoiceItem saveNewInvoiceItem (InvoiceItem invoiceItem){ return invoiceItemRepository.saveAndFlush(invoiceItem); }

}
