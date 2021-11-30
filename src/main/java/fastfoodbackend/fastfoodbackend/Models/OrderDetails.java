package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdOrderDetails;
    @ManyToOne
    @JoinColumn(name="IdOrderType", nullable=false)
    private OrderType IdOrderType;
    private String Name;
    private String Description;
    private Date DateInsert;

    public OrderDetails() {
    }

    public OrderDetails(OrderType idOrderType, String name, String description, Date dateInsert) {
        IdOrderType = idOrderType;
        Name = name;
        Description = description;
        DateInsert = dateInsert;
    }

    public Integer getIdOrderDetails() {
        return IdOrderDetails;
    }

    public void setIdOrderDetails(Integer idOrderDetails) {
        IdOrderDetails = idOrderDetails;
    }

    public OrderType getIdOrderType() {
        return IdOrderType;
    }

    public void setIdOrderType(OrderType idOrderType) {
        IdOrderType = idOrderType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDateInsert() {
        return DateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        DateInsert = dateInsert;
    }
}
