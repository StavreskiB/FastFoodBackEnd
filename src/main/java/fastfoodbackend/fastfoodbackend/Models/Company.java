package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdCompany;
    private String Name;
    private String City;
    private String Description;
    private Integer IdCompanyType;



    public Company() {
    }

    public Company(String name, String city, String description, Integer idCompanyType) {
        Name = name;
        City = city;
        Description = description;
        IdCompanyType = idCompanyType;
    }

    public Integer getIdCompany() {
        return IdCompany;
    }

    public void setIdCompany(Integer idCompany) {
        IdCompany = idCompany;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getIdCompanyType() {
        return IdCompanyType;
    }

    public void setIdCompanyType(Integer idCompanyType) {
        IdCompanyType = idCompanyType;
    }
}
