package io.smartraise.service;

import io.smartraise.model.donations.Donation;
import io.smartraise.model.fundraise.Event;
import io.smartraise.model.fundraise.Organization;

import java.util.List;

public interface EventService extends Service<Event> {

    List<Donation> getDonations(Event event);

    List<Event> getEvents(Organization organization);

    List<Donation> getAllDonations(Organization organization);
}
