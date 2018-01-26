package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Payment;
import io.smartraise.model.accounts.Donor;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Model representing a donation made by {@link Donor} for {@link Event} contributing to {@link Charity}
 */
public class Donation {

    @Id
    private final String donationId;
    private final String donor;
    private Payment payment;
    private final String charity;
    private final String organization;
    private final String event;
    private final float amount;
    private final Date date;

    /**
     * Creates a new donation
     */
    public Donation() {
        this.donationId = UUID.randomUUID().toString();
        this.donor = "";
        this.payment = new Payment();
        this.charity = "";
        this.event = "";
        this.organization = "";
        this.amount = 0;
        this.date = Calendar.getInstance().getTime();
    }

    public String getDonationId() {
        return donationId;
    }

    public String getDonor() {
        return donor;
    }

    public Object getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCharity() {
        return charity;
    }

    public String getOrganization() {
        return organization;
    }

    public String getEvent() {
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

    public float getAmount() {
        return amount;
    }
}
