package fastfoodbackend.fastfoodbackend.Security.Models;

public class TokenRes {
    private final String jwt;
    private String clientEmail;

    public TokenRes(String jwt, String clientEmail) {
        this.jwt = jwt;
        this.clientEmail = clientEmail;
    }

    public String getJwt() {
        return jwt;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
