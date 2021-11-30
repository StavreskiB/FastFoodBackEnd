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
    private Double Price;
    private Date DateInsert;
    @ManyToOne
    @JoinColumn(name="IdNorms", nullable=false)
    private Norms IdNorms;

    public Product() {
    }

    public Product(ProductType idProductType, String name, Double price, Date dateInsert, Norms idNorms) {
        IdProductType = idProductType;
        Name = name;
        Price = price;
        DateInsert = dateInsert;
        IdNorms = idNorms;
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

    public Norms getIdNorms() {
        return IdNorms;
    }

    public void setIdNorms(Norms idNorms) {
        IdNorms = idNorms;
    }
}
