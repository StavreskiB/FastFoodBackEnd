package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.Bills;
import fastfoodbackend.fastfoodbackend.Models.OrderStatus;
import fastfoodbackend.fastfoodbackend.Models.OrderType;
import fastfoodbackend.fastfoodbackend.Repository.BillsRepository;
import fastfoodbackend.fastfoodbackend.Repository.OrderStatusRepository;
import fastfoodbackend.fastfoodbackend.Repository.OrderTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillsService {

    @Autowired
    private BillsRepository billsRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;


    public static Specification<OrderStatus> getOsByNameSpec(String name) {
        return (Specification<OrderStatus>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (name != "" || name != null) {
                predicates.add(cb.equal(root.get("Name"), name));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<OrderStatus> getOsByName (Specification<OrderStatus> spec){
        return orderStatusRepository.findAll(spec);
    }

    public static Specification<OrderType> getOtByNameSpec(String name) {
        return (Specification<OrderType>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (name != "" || name != null) {
                predicates.add(cb.equal(root.get("Name"), name));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getBillsById(Integer IdBills) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (IdBills > 0|| IdBills != null) {
                predicates.add(cb.equal(root.get("IdBills"), IdBills));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getBillsForMark(String name, Integer companyId, Integer status, Integer type) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (name != "" || name != null) {
                predicates.add(cb.equal(root.get("Tables"), name));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdOrderStatus"), status));

                predicates.add(cb.equal(root.get("IdOrderType"), type));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> convertMarkToBills(String name, Integer companyId) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (name != "" || name != null) {
                predicates.add(cb.equal(root.get("Tables"), name));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdOrderStatus"), 1));

            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> checkForExist(Integer productId, Integer companyId, String tableName) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (productId != null) {
                predicates.add(cb.equal(root.get("IdProduct"), productId));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("Tables"), tableName));

                predicates.add(cb.equal(root.get("IdOrderStatus"), 2));

            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    public static Specification<Bills> getBillsForReports(String companyId, String dateInsert, String shiftStart, String shiftEnd) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId != "" && dateInsert != "" && shiftStart != "" && shiftEnd != "") {
                predicates.add(cb.equal(root.get("DateInsert"), dateInsert));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.greaterThanOrEqualTo(root.get("TimeInsert"), shiftStart));

                predicates.add(cb.lessThanOrEqualTo(root.get("TimeInsert"), shiftEnd));

            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getBillsByProductId(Integer idProduct, String companyId, String dateInsert, String shiftStart, String shiftEnd) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId != "" && dateInsert != "" && shiftStart != "" && shiftEnd != "") {
                predicates.add(cb.equal(root.get("DateInsert"), dateInsert));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("idProduct"), idProduct));

                predicates.add(cb.greaterThanOrEqualTo(root.get("TimeInsert"), shiftStart));

                predicates.add(cb.lessThanOrEqualTo(root.get("TimeInsert"), shiftEnd));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    public static Specification<Bills> getBillsItem(String name, Integer companyId) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (name != "" || name != null) {
                predicates.add(cb.equal(root.get("Tables"), name));

                predicates.add(cb.equal(root.get("CompanyId"), companyId));

                predicates.add(cb.equal(root.get("IdOrderStatus"), 2));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Bills> getReservedBills(String name) {
        return (Specification<Bills>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (name != "" || name != null) {
                predicates.add(cb.equal(root.get("CompanyId"), name));

                predicates.add(cb.notEqual(root.get("IdOrderStatus"), 3));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }





    public List<OrderType> getOtByName (Specification<OrderType> spec){
        return orderTypeRepository.findAll(spec);
    }

    public List<Bills> getBillsBySpec (Specification<Bills> spec){
        return billsRepository.findAll(spec);
    }

    public Bills saveBills(Bills bills){
        return billsRepository.saveAndFlush(bills);}

    public void deleteBills (Bills bills){
         billsRepository.delete(bills);
    }


}
