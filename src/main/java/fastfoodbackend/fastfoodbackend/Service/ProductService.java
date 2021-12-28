package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.Norms;
import fastfoodbackend.fastfoodbackend.Models.Product;
import fastfoodbackend.fastfoodbackend.Models.ProductType;
import fastfoodbackend.fastfoodbackend.Models.Stock;
import fastfoodbackend.fastfoodbackend.Repository.NormRepository;
import fastfoodbackend.fastfoodbackend.Repository.ProductRepository;
import fastfoodbackend.fastfoodbackend.Repository.ProductTypeRepository;
import fastfoodbackend.fastfoodbackend.Repository.StockRepository;
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

    public static Specification<Norms> getNormsByProductId(Integer productId) {
        return (Specification<Norms>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productId > 0 || productId != null) {
                predicates.add(cb.equal(root.get("IdProductN"), productId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Norms> getNormsById(Integer idNorms) {
        return (Specification<Norms>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (idNorms > 0 || idNorms != null) {
                predicates.add(cb.equal(root.get("I00dNorms"), idNorms));
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


}
