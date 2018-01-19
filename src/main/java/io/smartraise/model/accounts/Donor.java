package io.smartraise.model.accounts;

import io.smartraise.model.donations.Donation;
import io.smartraise.model.Privilege;
import io.smartraise.model.donations.Payment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

public class Donor {

    @Id
    private final String username;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Privilege privilege;
    private Payment payment;
    private final Set<Donation> donations;

    public Donor(){
        this.username = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.privilege = Privilege.DONOR_NOT_VERIFIED;
        this.payment = new Payment();
        this.donations = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<Donation> getDonations() {
        return donations;
    }

    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }
}
