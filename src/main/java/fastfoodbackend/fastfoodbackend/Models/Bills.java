package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "bills")
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdBills;
    private String Tables;
    private String Employee;
    private String DateInsert;
    private String TimeInsert;


    @ManyToOne
    @JoinColumn(name="IdOrderStatus", nullable=false)
    private OrderStatus IdOrderStatus;

    @ManyToOne
    @JoinColumn(name="IdOrderType", nullable=false)
    private OrderType IdOrderType;

    @ManyToOne
    @JoinColumn(name="IdProduct", nullable=false)
    private Product IdProduct;

    private Double Quantity;
    private String Address;
    private String Addons;
    private Integer CompanyId;

    public Bills (){}

    public Bills(String tables, String employee, String dateInsert, String timeInser, OrderStatus idOrderStatus, OrderType idOrderType, Product idProduct, Double quantity, String address, String addons, Integer companyId) {
        Tables = tables;
        Employee = employee;
        DateInsert = dateInsert;
        TimeInsert = timeInser;
        IdOrderStatus = idOrderStatus;
        IdOrderType = idOrderType;
        IdProduct = idProduct;
        Quantity = quantity;
        Address = address;
        Addons = addons;
        CompanyId = companyId;
    }

    public Integer getIdBills() {
        return IdBills;
    }

    public void setIdBills(Integer idBills) {
        IdBills = idBills;
    }

    public String getTables() {
        return Tables;
    }

    public void setTables(String tables) {
        Tables = tables;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getDateInsert() {
        return DateInsert;
    }

    public void setDateInsert(String dateInsert) {
        DateInsert = dateInsert;
    }

    public String getTimeInser() {
        return TimeInsert;
    }

    public void setTimeInser(String timeInser) {
        TimeInsert = timeInser;
    }

    public OrderStatus getIdOrderStatus() {
        return IdOrderStatus;
    }

    public void setIdOrderStatus(OrderStatus idOrderStatus) {
        IdOrderStatus = idOrderStatus;
    }

    public OrderType getIdOrderType() {
        return IdOrderType;
    }

    public void setIdOrderType(OrderType idOrderType) {
        IdOrderType = idOrderType;
    }

    public Product getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Product idProduct) {
        IdProduct = idProduct;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddons() {
        return Addons;
    }

    public void setAddons(String addons) {
        Addons = addons;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }
}
