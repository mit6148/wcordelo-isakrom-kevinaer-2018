package io.smartraise.model.accounts;

import io.smartraise.model.fundraise.Organization;
import io.smartraise.model.Privilege;
import io.smartraise.util.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Model representing a fundraising member
 */
public class Member {

    @Id
    private final String username;
    @Indexed(unique = true)
    @Deprecated
    private String email;
    @Deprecated
    private String firstName;
    @Deprecated
    private String lastName;
    private Privilege privilege;
    private final Set<String> organizations;

    @DBRef
    @CascadeSave
    private ContactInformation contactInformation;

    public Member(String email, String username) {
        this.firstName = "";
        this.lastName = "";
        this.username = username;
        this.email = email;
        this.privilege = Privilege.MEMBER_NOT_VERIFIED;
        this.organizations = new HashSet<>();
        this.contactInformation = new ContactInformation(this.username);
    }

    public Member(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = "";
        this.privilege = Privilege.NOT_VISIBLE;
        this.organizations = new HashSet<>();
        this.contactInformation = new ContactInformation(this.username);
    }

    public Member() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.username = "";
        this.privilege = Privilege.MEMBER_NOT_VERIFIED;
        this.organizations = new HashSet<>();
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
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

    public Set<String> getOrganizations() {
        return this.organizations;
    }

    public void addOrganization(String uuid){ this.organizations.add(uuid); }

    public void removeOrganization(String uuid){ this.organizations.remove(uuid); }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        return this.getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member that = (Member) obj;
            return this.getUsername().equals(that.getUsername());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Member {Username: %s, Email: %s}", this.username, this.email);
    }
}
