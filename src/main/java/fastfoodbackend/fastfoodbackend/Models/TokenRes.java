package fastfoodbackend.fastfoodbackend.Models;

public class TokenRes {
    private final String jwt;
    private String userEmail;
    private String userType;
    private Integer companyId;

    public TokenRes(String jwt, String userEmail, String userType, Integer companyId) {
        this.jwt = jwt;
        this.userEmail = userEmail;
        this.userType = userType;
        this.companyId = companyId;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
