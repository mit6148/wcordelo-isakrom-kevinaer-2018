package io.smartraise.model.accounts;

import io.smartraise.model.Privilege;
import io.smartraise.model.donations.Payment;
import org.springframework.data.annotation.Id;

public class Charity {

    @Id
    private final long charityId;
    private String name;
    private String description;
    private Privilege privilege;
    private Payment payment;
    private String website;

    public Charity() {
        this.charityId = 0;
        this.name = "";
        this.description = "";
        this.privilege = Privilege.CHARITY_NOT_VERIFIED;
        this.payment = new Payment();
        this.website = "";
    }

    public long getCharityId() {
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
