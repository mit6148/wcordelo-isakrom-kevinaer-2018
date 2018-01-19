package io.smartraise.model.accounts;

import io.smartraise.model.fundraise.Organization;
import io.smartraise.model.Privilege;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

public class Member {

    @Id
    private final String username;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Privilege privilege;
    private final Set<Organization> organizations;

    public Member(String email, String username) {
        this.firstName = "";
        this.lastName = "";
        this.username = username;
        this.email = email;
        this.privilege = Privilege.MEMBER_NOT_VERIFIED;
        this.organizations = new HashSet<>();
    }

    public Member(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = "";
        this.privilege = Privilege.NOT_VISIBLE;
        this.organizations = new HashSet<>();
    }

    public Member() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.username = "";
        this.privilege = Privilege.MEMBER_NOT_VERIFIED;
        this.organizations = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Set<Organization> getOrganizations() {
        return new HashSet<>(organizations);
    }

    public String getUsername() {
        return username;
    }
}
