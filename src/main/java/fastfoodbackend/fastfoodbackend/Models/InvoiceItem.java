package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "invoiceitem")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdInvoiceItem;

    @ManyToOne
    @JoinColumn(name="IdProduct", nullable=false)
    private Product IdProduct;

    @ManyToOne
    @JoinColumn(name="IdInvoice", nullable=false)
    private Invoice IdInvoice;

    private Double Quantity;
    private Double Price;
    private Integer CompanyId;


    public InvoiceItem(){}


    public InvoiceItem(Product idProduct, Invoice idInvoice, Double quantity, Double price, Integer companyId) {
        IdProduct = idProduct;
        IdInvoice = idInvoice;
        Quantity = quantity;
        Price = price;
        CompanyId = companyId;
    }

    public Integer getIdInvoiceItem() {
        return IdInvoiceItem;
    }

    public void setIdInvoiceItem(Integer idInvoiceItem) {
        IdInvoiceItem = idInvoiceItem;
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

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }
}
