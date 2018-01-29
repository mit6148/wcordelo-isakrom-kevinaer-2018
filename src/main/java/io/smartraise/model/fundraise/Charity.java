package io.smartraise.model.fundraise;

import io.smartraise.model.Privilege;
import io.smartraise.model.accounts.Address;
import io.smartraise.model.accounts.Payment;
import io.smartraise.util.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

public class Charity {

    @Id
    private final String charityId;
    private String name;
    private String description;
    @Deprecated
    private Privilege privilege;
//    @DBRef
//    @CascadeSave
//    private Payment payment;
    private String website;
    @DBRef
    @CascadeSave
    private Address address;

    public Charity() {
        this.charityId = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.website = "";
        this.address = new Address();
    }

    public String getCharityId() {
        return charityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
//
//    public Payment getPayment() {
//        return payment;
//    }
//
//    public void setPayment(Payment payment) {
//        this.payment = payment;
//    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
