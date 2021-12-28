package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "orderstatus")
public class OrderStatus {
    @Id
    private Integer IdOrderStatus;
    private String Name;

    public OrderStatus() {
    }

    public OrderStatus(Integer idOrderStatus, String name) {
        IdOrderStatus = idOrderStatus;
        Name = name;
    }

    public Integer getIdOrderStatus() {
        return IdOrderStatus;
    }

    public void setIdOrderStatus(Integer idOrderStatus) {
        IdOrderStatus = idOrderStatus;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
