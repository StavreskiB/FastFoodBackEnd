package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "ordertype")
public class OrderType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdOrderType;
    private String Name;

    public OrderType() {
    }

    public OrderType(String name) {
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
