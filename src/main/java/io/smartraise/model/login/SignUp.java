package io.smartraise.model.login;

public class SignUp {

    private String firstName;
    private String lastName;
    private Credential.UserType type;
    private String username;
    private String email;
    private String password;

    public SignUp(){
        type = Credential.UserType.NONE;
        email = "";
        password = "";
        username = "";
        firstName = "";
        lastName = "";
    }

    public SignUp(String email, String username, String password) {
        type = Credential.UserType.NONE;
        this.email = email;
        this.password = password;
        this.username = username;
        firstName = "";
        lastName = "";
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
