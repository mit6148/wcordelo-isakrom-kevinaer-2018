package io.smartraise.model.fundraise;

import org.apache.commons.math3.util.Precision;

public class DonationRequest {

    private String eventId;
    private String donorId;
    private double amount;

    public DonationRequest() {
        eventId = "";
        donorId = "";
        amount = 0;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = Precision.round(amount, 2);
    }
}
