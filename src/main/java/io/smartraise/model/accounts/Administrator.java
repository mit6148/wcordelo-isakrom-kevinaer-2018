package io.smartraise.model.accounts;

import io.smartraise.model.Privilege;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
@Deprecated
public class Administrator {

    @Id
    private final String username;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Privilege privilege;

    public Administrator() {
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.email = "";
        this.privilege = Privilege.ADMIN_NOT_VERIFIED;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Privilege getPrivilege() {
        return privilege;
    }
}
