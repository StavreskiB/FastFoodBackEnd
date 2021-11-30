package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "norms")
public class Norms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdNorms;
    @ManyToOne
    @JoinColumn(name="IdProduct", nullable=false)
    private Product IdProduct;
    private Double Quantity;

    public Norms() {
    }

    public Norms(Product idProduct, Double quantity) {
        IdProduct = idProduct;
        Quantity = quantity;
    }

    public Integer getIdNorms() {
        return IdNorms;
    }

    public void setIdNorms(Integer idNorms) {
        IdNorms = idNorms;
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
}
