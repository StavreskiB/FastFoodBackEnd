package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUser;

    @ManyToOne
    @JoinColumn(name="IdUserType", nullable=false)
    private UserType IdUserType;
    private String Name;
    private String Surname;
    private String Password;
    private Date DateInsert;
    private String Email;
    private Integer CompanyId;

    public User() {
    }

    public User(Integer idUser, UserType idUserType, String name, String surname, String password, Date dateInsert, String email, Integer companyId) {
        IdUser = idUser;
        IdUserType = idUserType;
        Name = name;
        Surname = surname;
        Password = password;
        DateInsert = dateInsert;
        Email = email;
        CompanyId = companyId;
    }

    public Integer getIdUser() {
        return IdUser;
    }

    public void setIdUser(Integer idUser) {
        IdUser = idUser;
    }

    public UserType getIdUserType() {
        return IdUserType;
    }

    public void setIdUserType(UserType idUserType) {
        IdUserType = idUserType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Date getDateInsert() {
        return DateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        DateInsert = dateInsert;
    }

    public Integer getCompanyId() { return CompanyId; }

    public void setCompanyId(Integer companyId) { CompanyId = companyId; }
}
