package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "producttype")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdProductType;
    private String Name;

    public ProductType() {}

    public ProductType(String name) {
        Name = name;
    }

    public Integer getIdProductType() {
        return IdProductType;
    }

    public void setIdProductType(Integer idProductType) {
        IdProductType = idProductType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
