package io.smartraise.model.accounts;

import io.smartraise.model.Privilege;
import io.smartraise.model.donations.Payment;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Charity {

    @Id
    private final String charityId;
    private String name;
    private String description;
    private Privilege privilege;
    private Payment payment;
    private String website;

    public Charity() {
        this.charityId = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.privilege = Privilege.CHARITY_NOT_VERIFIED;
        this.payment = new Payment();
        this.website = "";
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

    public Object getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
