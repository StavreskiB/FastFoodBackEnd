package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "usertype")
public class UserType {
    @Id
    private Integer IdUserType;
    private String Name;

    public UserType() {
    }

    public UserType(String name) {
        Name = name;
    }

    public Integer getIdUserType() {
        return IdUserType;
    }

    public void setIdUserType(Integer idUserType) {
        IdUserType = idUserType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
