package io.smartraise.model.accounts.login;

public class LogIn {

    private final Credential.Type type;
    private final String username;
    private final String email;
    private final String password;

    public LogIn(){
        type = Credential.Type.MEMBER;
        email = "";
        password = "";
        username = "";
    }

    public LogIn(String email, String password) {
        type = Credential.Type.MEMBER;
        this.email = email;
        this.password = password;
        this.username = "";
    }

    public Credential.Type getType() {
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
