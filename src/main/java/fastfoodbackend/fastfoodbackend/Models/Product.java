package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdProduct;
    @ManyToOne
    @JoinColumn(name="IdProductType", nullable=false)
    private ProductType IdProductType;
    private String Name;
    private Double Quantity;
    private Double Price;
    private Date DateInsert;
    private Integer CompanyId;
    private Integer Status;

    public Product() {
    }

    public Product(ProductType idProductType, String name, Double quantity, Double price, Date dateInsert, Integer companyId, Integer status) {
        IdProductType = idProductType;
        Name = name;
        Quantity = quantity;
        Price = price;
        DateInsert = dateInsert;
        CompanyId = companyId;
        Status = status;
    }

    public Integer getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Integer idProduct) {
        IdProduct = idProduct;
    }

    public ProductType getIdProductType() {
        return IdProductType;
    }

    public void setIdProductType(ProductType idProductType) {
        IdProductType = idProductType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Date getDateInsert() {
        return DateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        DateInsert = dateInsert;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
