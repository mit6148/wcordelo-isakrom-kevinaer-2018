package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Charity;
import io.smartraise.model.donations.Donation;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Event {

    @Id
    private final String eventId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private final String organization;
    private final String charity;
    private String image;

    public Event() {
        this.eventId = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.startDate = Calendar.getInstance().getTime();
        this.endDate = Calendar.getInstance().getTime();
        this.organization = "";
        this.charity = "";
        this.image = "";
    }

    public String getEventId() {
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

    public String getOrganization() {
        return organization;
    }

    public String getCharity() {
        return charity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
