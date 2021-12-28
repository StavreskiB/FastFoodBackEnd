package fastfoodbackend.fastfoodbackend.Class;

public class InvoiceItem {
    private Integer productId;
    private Integer invoiceName;
    private Double quantity;
    private Double price;
    private Integer companyId;

    public InvoiceItem(){}


    public InvoiceItem(Integer productId, Integer invoiceName, Double quantity, Double price, Integer companyId) {
        this.productId = productId;
        this.invoiceName = invoiceName;
        this.quantity = quantity;
        this.price = price;
        this.companyId = companyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(Integer invoiceName) {
        this.invoiceName = invoiceName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
