package io.smartraise.model.accounts;

import io.smartraise.model.Privilege;
import io.smartraise.model.fundraise.Organization;
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
    private String username;
    @Deprecated
    private final Set<Privilege> privilege;
    private Set<String> organizations;
    private double donation;

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
        this.donation = 0;
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

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrganizations(Set<String> organizations) {
        this.organizations = organizations;
    }

    public double getDonation() {
        return donation;
    }

    public void setDonation(double donation) {
        this.donation = donation;
    }
}
