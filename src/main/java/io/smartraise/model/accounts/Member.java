package io.smartraise.model.accounts;

import io.smartraise.model.Privilege;
import io.smartraise.util.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;

/**
 * Model representing a fundraising member
 */
public class Member {

    @Id
    private final String username;
    private final Set<Privilege> privilege;
    private final Set<String> organizations;

    @DBRef
    @CascadeSave
    private ContactInformation contactInformation;

//    @DBRef
//    @CascadeSave
//    private Payment payment;

    public Member(String username) {
        this.username = username;
        this.privilege = new HashSet<>();
        this.organizations = new HashSet<>();
        this.contactInformation = new ContactInformation(this.username);
//        this.payment = new Payment();
    }

    public Member() {
        this.username = "";
        this.privilege = new HashSet<>();
        this.organizations = new HashSet<>();
//        this.payment = new Payment();
    }

//    public Member(String username, Set<String> organizations, ContactInformation contactInformation) {
//        this.username = username;
//        this.organizations = organizations;
//        this.contactInformation = contactInformation;
//        this.privilege = new HashSet<>();
//    }

    public Set<Privilege> getPrivilege() {
        return privilege;
    }

    public void addPrivilege(Privilege privilege) {
        this.privilege.add(privilege);
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
        return String.format("Member {Username: %s, Email: %s}", this.username, this.contactInformation.getEmail());
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

//    public Payment getPayment() {
//        return payment;
//    }

//    public void setPayment(Payment payment) {
//        this.payment = payment;
//    }

//    public Member getPrivate() {
//        return new Member(this.username, this.getOrganizations(), this.contactInformation.getPrivate());
//    }

}
