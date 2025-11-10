package es.zenith.levelestapi.dto;

public class LoginForm {
    private String email;
    private String password;

    public LoginForm(){}
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
