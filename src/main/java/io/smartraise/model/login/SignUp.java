package io.smartraise.model.login;

import java.util.HashMap;
import java.util.Map;

/**
 * Model representing an attempt to sign up for an account of type {@link Credential.UserType}
 */
public class SignUp {

    private Credential.UserType type;
    private String username;
    private String email;
    private String password;
    private Map<String, Object> account;

    public SignUp(){
        type = Credential.UserType.NONE;
        email = "";
        password = "";
        username = "";
        account = new HashMap<>();
    }

    public SignUp(String email, String username, String password) {
        type = Credential.UserType.NONE;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Credential.UserType getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() { return username; }

    public void setType(Credential.UserType type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getAccount() {
        return new HashMap<>(account);
    }
}
