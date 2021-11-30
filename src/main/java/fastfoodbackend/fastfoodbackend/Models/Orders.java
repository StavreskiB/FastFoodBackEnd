package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdOrder;
    @ManyToOne
    @JoinColumn(name="IdProduct", nullable=false)
    private Product IdProduct;
    @ManyToOne
    @JoinColumn(name="IdOrderDetails", nullable=false)
    private OrderDetails IdOrderDetails;
    private double Quantity;
    private double TotalPrice;

    public Orders(){}

    public Orders(Product idProduct, OrderDetails idOrderDetails, double quantity, double totalPrice) {
        IdProduct = idProduct;
        IdOrderDetails = idOrderDetails;
        Quantity = quantity;
        TotalPrice = totalPrice;
    }

    public Integer getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Integer idOrder) {
        IdOrder = idOrder;
    }

    public Product getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Product idProduct) {
        IdProduct = idProduct;
    }

    public OrderDetails getIdOrderDetails() {
        return IdOrderDetails;
    }

    public void setIdOrderDetails(OrderDetails idOrderDetails) {
        IdOrderDetails = idOrderDetails;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }
}
