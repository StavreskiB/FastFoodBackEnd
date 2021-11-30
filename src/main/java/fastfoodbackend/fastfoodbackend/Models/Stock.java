package fastfoodbackend.fastfoodbackend.Models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdStock;
    @ManyToOne
    @JoinColumn(name="IdProduct", nullable=false)
    private Product IdProduct;

    @ManyToOne
    @JoinColumn(name="IdInvoice", nullable=false)
    private Invoice IdInvoice;
    private Double Quantity;
    private Date DateInsert;

    public Stock(){}

    public Stock(Product idProduct, Invoice idInvoice, Double quantity, Date dateInsert) {
        IdProduct = idProduct;
        IdInvoice = idInvoice;
        Quantity = quantity;
        DateInsert = dateInsert;
    }

    public Integer getIdStock() {
        return IdStock;
    }

    public void setIdStock(Integer idStock) {
        IdStock = idStock;
    }

    public Product getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Product idProduct) {
        IdProduct = idProduct;
    }

    public Invoice getIdInvoice() {
        return IdInvoice;
    }

    public void setIdInvoice(Invoice idInvoice) {
        IdInvoice = idInvoice;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public Date getDateInsert() {
        return DateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        DateInsert = dateInsert;
    }
}
