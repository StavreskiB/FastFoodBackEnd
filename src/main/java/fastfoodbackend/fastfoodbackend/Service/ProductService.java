package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.*;
import fastfoodbackend.fastfoodbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private NormRepository normRepository;

    @Autowired
    private BillsRepository billsRepository;

    public List<ProductType> getAllProductType(){
        return productTypeRepository.findAll();
    }

    public static Specification<Product> getAllProductBySpec(Integer companyId) {
        return (Specification<Product>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    public static Specification<Product> getAllProductForSell(Integer companyId) {
        return (Specification<Product>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdProductType"), 1));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Product> getProductById(Integer productId) {
        return (Specification<Product>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productId > 0 || productId != null) {
                predicates.add(cb.equal(root.get("IdProduct"), productId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Stock> getStockByProductId(Integer productId) {
        return (Specification<Stock>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productId > 0 || productId != null) {
                predicates.add(cb.equal(root.get("IdProduct"), productId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Stock> getAllStockBySpec(Integer companyId) {
        return (Specification<Stock>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Stock> getStockLimit(Integer companyId, Double quantity) {
        return (Specification<Stock>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.lessThanOrEqualTo(root.get("Quantity"), quantity));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Norms> getNormsByProductIdN(Integer productId) {
        return (Specification<Norms>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productId > 0 || productId != null) {
                predicates.add(cb.equal(root.get("IdProductN"), productId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getBillsForUpdate(Integer companyId, String tables, Integer productId) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productId > 0 || productId != null) {
                predicates.add(cb.equal(root.get("IdProduct"), productId));

                predicates.add(cb.equal(root.get("Tables"), tables));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdOrderStatus"), 2));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getTotalDelivery(Integer companyId, String now) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdOrderType"), 2));

                predicates.add(cb.equal(root.get("DateInsert"), now));

            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getTotalBills(Integer companyId, String now) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdOrderType"), 1));

                predicates.add(cb.equal(root.get("DateInsert"), now));

            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getAllBills(Integer companyId, String now) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("DateInsert"), now));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }






    public static Specification<Norms> getNormsById(Integer idNorms) {
        return (Specification<Norms>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (idNorms > 0 || idNorms != null) {
                predicates.add(cb.equal(root.get("IdNorms"), idNorms));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Stock> getStockById(Integer stockId) {
        return (Specification<Stock>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (stockId > 0 || stockId != null) {
                predicates.add(cb.equal(root.get("IdStock"), stockId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    public static Specification<Product> getAllProductByName(String productName) {
        return (Specification<Product>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productName != "" || productName != null) {
                predicates.add(cb.equal(root.get("Name"), productName));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<Norms> getNormsBySpec (Specification<Norms> spec){
        return normRepository.findAll(spec);
    }

    public List<Product> getProductById (Specification<Product> spec){
        return productRepository.findAll(spec);
    }

    public List<Stock> getAllStock (Specification<Stock> spec){
        return stockRepository.findAll(spec);
    }

    public Product saveNewProduct(Product product){return productRepository.saveAndFlush(product);}

    public Stock saveNewStock(Stock stock){return stockRepository.saveAndFlush(stock);}

    public Norms saveNewNorms(Norms norms) {return normRepository.saveAndFlush(norms);}

    public List<Product> getAllProduct(Specification<Product> spec){
        return productRepository.findAll(spec);
    }

    public List<Product> getAllProductForSell(Specification<Product> spec){
        return productRepository.findAll(spec);
    }

    public List<Bills> getBillsBySpec(Specification<Bills> spec){
        return billsRepository.findAll(spec);
    }


}
