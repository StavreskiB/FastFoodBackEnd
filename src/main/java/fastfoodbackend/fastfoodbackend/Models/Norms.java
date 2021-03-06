package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "norms")
public class Norms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdNorms;

    private Integer IdProductN;

    @ManyToOne
    @JoinColumn(name="IdProduct", nullable=false)
    private Product IdProduct;
    private Double Quantity;
    private Integer CompanyId;

    public Norms() {
    }

    public Norms(Integer idProductN, Product idProduct, Double quantity, Integer companyId) {
        IdProductN = idProductN;
        IdProduct = idProduct;
        Quantity = quantity;
        CompanyId = companyId;
    }

    public Integer getIdNorms() {
        return IdNorms;
    }

    public void setIdNorms(Integer idNorms) {
        IdNorms = idNorms;
    }

    public Integer getIdProductN() {
        return IdProductN;
    }

    public void setIdProductN(Integer idProductN) {
        IdProductN = idProductN;
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

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }
}
