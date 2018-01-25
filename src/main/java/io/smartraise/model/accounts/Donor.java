package io.smartraise.model.accounts;

import io.smartraise.model.donations.Donation;
import io.smartraise.model.Privilege;
import io.smartraise.model.donations.Payment;
import io.smartraise.util.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Model representing a user that wants to just donate
 */
public class Donor {

    @Id
    private final String username;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Privilege privilege;
    @DBRef
    @CascadeSave
    private Payment payment;

    /**
     * Creates a new Donor
     */
    public Donor(){
        this.username = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.privilege = Privilege.DONOR_NOT_VERIFIED;
        this.payment = new Payment();
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

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donor) {
            Donor that = (Donor) obj;
            return this.username.equals(that.username);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Donor {Username: %s, Email: %s}", this.username, this.email);
    }
}
