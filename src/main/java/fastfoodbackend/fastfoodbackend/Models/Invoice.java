package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdInvoice;
    private String Name;
    private String CompanyName;
    private Double Price;
    private Date DateInsert;
    private Date Deadline;
    private boolean Status;

    public Invoice(){}

    public Invoice(String name, String companyName, Double price, Date dateInsert, Date deadline, boolean status) {
        Name = name;
        CompanyName = companyName;
        Price = price;
        DateInsert = dateInsert;
        Deadline = deadline;
        Status = status;
    }

    public Integer getIdInvoice() {
        return IdInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        IdInvoice = idInvoice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Date getDateInsert() {
        return DateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        DateInsert = dateInsert;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
