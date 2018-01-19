package io.smartraise.model.login;

public class LogIn {

    private final Credential.UserType type;
    private final String username;
    private final String email;
    private final String password;

    public LogIn(){
        type = Credential.UserType.MEMBER;
        email = "";
        password = "";
        username = "";
    }

    public LogIn(String email, String password) {
        type = Credential.UserType.MEMBER;
        this.email = email;
        this.password = password;
        this.username = "";
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
}
