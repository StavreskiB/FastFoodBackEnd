package fastfoodbackend.fastfoodbackend.Models;

public class TokenReq {
    private String email;
    private String pasword;

    public TokenReq() {
    }

    public TokenReq(String email, String pasword) {
        this.email = email;
        this.pasword = pasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
