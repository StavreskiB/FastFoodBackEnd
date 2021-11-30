package fastfoodbackend.fastfoodbackend.Models;

public class Token {
    private String Email;
    private String Password;

    public Token() {}

    public Token(String email, String password) {
        Email = email;
        Password = password;
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
}
