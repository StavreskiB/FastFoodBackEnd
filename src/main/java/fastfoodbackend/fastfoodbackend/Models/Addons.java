package fastfoodbackend.fastfoodbackend.Models;


import javax.persistence.*;

@Entity
@Table(name = "addons")
public class Addons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdAddons;
    private String Name;
    private Integer CompanyId;

    public Addons(){}

    public Addons(String name, Integer companyId) {
        Name = name;
        CompanyId = companyId;
    }

    public Integer getIdAddons() {
        return IdAddons;
    }

    public void setIdAddons(Integer idAddons) {
        IdAddons = idAddons;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }
}
