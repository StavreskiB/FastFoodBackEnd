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
    private Integer IdInvoice;
    private Double Quantity;
    private Date DateInsert;
    private Integer CompanyId;
    private Integer Status;

    public Stock(){}

    public Stock(Product idProduct, Integer idInvoice, Double quantity, Date dateInsert, Integer companyId, Integer status) {
        IdProduct = idProduct;
        IdInvoice = idInvoice;
        Quantity = quantity;
        DateInsert = dateInsert;
        CompanyId = companyId;
        Status = status;
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

    public Integer getIdInvoice() {
        return IdInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
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
