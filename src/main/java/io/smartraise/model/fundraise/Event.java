package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Charity;
import io.smartraise.model.donations.Donation;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Event {

    @Id
    private final UUID eventId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private final Set<Donation> donations;
    private final Organization organization;
    private final Charity charity;
    private String image;

    public Event() {
        this.eventId = UUID.randomUUID();
        this.name = "";
        this.description = "";
        this.startDate = Calendar.getInstance().getTime();
        this.endDate = Calendar.getInstance().getTime();
        this.donations = new HashSet<>();
        this.organization = new Organization();
        this.charity = new Charity();
        this.image = "";
    }

    public UUID getEventId() {
        return eventId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Donation> getDonations() {
        return donations;
    }

    public void addDonation(Donation donation){
        this.donations.add(donation);
    }

    public Organization getOrganization() {
        return organization;
    }

    public Charity getCharity() {
        return charity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
