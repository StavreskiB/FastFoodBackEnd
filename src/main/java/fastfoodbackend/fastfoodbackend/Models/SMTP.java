package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "smtp")
public class SMTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdSmtp;
    private String Name;
    private String SmtpHost;
    private String Types;
    private String Email;
    private String Password;
    private String Authentication;
    private String Intervals;
    private String TLSEnable;
    private Integer Port;

    public SMTP() {
    }

    public SMTP(String name, String smtpHost, String types, String email, String password, String authentication, String intervals, String TLSEnable, Integer port) {
        Name = name;
        SmtpHost = smtpHost;
        Types = types;
        Email = email;
        Password = password;
        Authentication = authentication;
        Intervals = intervals;
        this.TLSEnable = TLSEnable;
        Port = port;
    }

    public Integer getIdSmtp() {
        return IdSmtp;
    }

    public void setIdSmtp(Integer idSmtp) {
        IdSmtp = idSmtp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSmtpHost() {
        return SmtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        SmtpHost = smtpHost;
    }

    public String getTypes() {
        return Types;
    }

    public void setTypes(String types) {
        Types = types;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAuthentication() {
        return Authentication;
    }

    public void setAuthentication(String authentication) {
        Authentication = authentication;
    }

    public String getIntervals() {
        return Intervals;
    }

    public void setIntervals(String intervals) {
        Intervals = intervals;
    }

    public String getTLSEnable() {
        return TLSEnable;
    }

    public void setTLSEnable(String TLSEnable) {
        this.TLSEnable = TLSEnable;
    }

    public Integer getPort() {
        return Port;
    }

    public void setPort(Integer port) {
        Port = port;
    }
}
