package io.smartraise.model.donations;

import io.smartraise.model.accounts.Charity;
import io.smartraise.model.accounts.Donor;
import io.smartraise.model.fundraise.Event;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Date;

public class Donation {

    @Id
    private final long donationId;
    private final Donor donor;
    private Payment payment;
    private final Charity charity;
    private final Event event;
    private final Date date;

    public Donation() {
        this.donationId = 0;
        this.donor = new Donor();
        this.payment = new Payment();
        this.charity = new Charity();
        this.event = new Event();
        this.date = Calendar.getInstance().getTime();
    }

    public long getDonationId() {
        return donationId;
    }

    public Donor getDonor() {
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

    public Event getEvent() {
        return event;
    }

    public Date getDate() {
        return date;
    }
}
