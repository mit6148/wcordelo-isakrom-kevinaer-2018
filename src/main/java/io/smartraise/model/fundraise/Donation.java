package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Payment;
import io.smartraise.util.CascadeSave;
import org.springframework.data.annotation.Id;
import io.smartraise.model.accounts.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Model representing a donation made by {@link Member} for {@link Event} contributing to {@link Charity}
 */
public class Donation {

    @Id
    private final String donationId;
    @DBRef
    @CascadeSave
    private final Member donor;
    private Payment payment;
    @DBRef
    @CascadeSave
    private final Charity charity;
    @DBRef
    @CascadeSave
    private final Organization organization;
    @DBRef
    @CascadeSave
    private final Event event;
    private final double amount;
    private final Date date;

    /**
     * Creates a new donation
     */
    public Donation() {
        this.donationId = UUID.randomUUID().toString();
        this.donor = new Member();
        this.payment = new Payment();
        this.charity = new Charity();
        this.event = new Event();
        this.organization = new Organization();
        this.amount = 0;
        this.date = Calendar.getInstance().getTime();
    }

    public Donation(Member donor, Event event, double amount) {
        this.donationId = UUID.randomUUID().toString();
        this.donor = donor;
        this.charity = event.getCharity();
        this.organization = event.getOrganization();
        this.event = event;
        this.amount = amount;
        this.date = Calendar.getInstance().getTime();
    }

    public String getDonationId() {
        return donationId;
    }

    public Member getDonor() {
        return donor;
    }

    public Object getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Charity getCharity() {
        return charity;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Event getEvent() {
        return event;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return this.getDonationId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donation) {
            Donation that = (Donation) obj;
            return this.getDonationId().equals(that.getDonationId());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Donation {Donor: %s, Event: %s, Charity: %s",
                donor, event, charity);
    }

    public double getAmount() {
        return amount;
    }
}
