package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "ordertype")
public class OrderType {

    @Id
    private Integer IdOrderType;
    private String Name;

    public OrderType() {
    }

    public OrderType(Integer idOrderType, String name) {
        IdOrderType = idOrderType;
        Name = name;
    }

    public Integer getIdOrderType() {
        return IdOrderType;
    }

    public void setIdOrderType(Integer idOrderType) {
        IdOrderType = idOrderType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
