package io.smartraise.service.impl;

import io.smartraise.dao.DonationDAO;
import io.smartraise.dao.EventDAO;
import io.smartraise.dao.OrganizationDAO;
import io.smartraise.model.Privilege;
import io.smartraise.model.donations.Donation;
import io.smartraise.model.fundraise.Event;
import io.smartraise.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class EventServiceImpl implements EventService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private EventDAO eventDAO;

    @Override
    public Event get(String id) throws Exception {
        return eventDAO.findOne(id);
    }

    @Override
    public Event create(Event event) throws Exception {
        if (isValid(event) && !eventDAO.exists(event.getEventId())) {
            eventDAO.save(event);
            return event;
        } else {
            throw new Exception("Not a valid event");
        }
    }

    @Override
    public void update(Event event) throws Exception {
        if (eventDAO.exists(event.getEventId())) {
            eventDAO.save(event);
        } else {
            throw new Exception("Event doesn't exist");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        if (eventDAO.exists(id)) {
            eventDAO.delete(id);
        }
    }

    @Override
    public List<Event> getEventsFromOrganization(String organizationId)throws Exception{
        if (organizationDAO.exists(organizationId.toString())) {
            return eventDAO.findAllByOrganization(organizationId);
        } else {
            throw new Exception("Organization doesn't exist");
        }
    }

    @Override
    public List<Event> getCurrentEventsFromOrganization(String organizationId) throws Exception {
        if (organizationDAO.exists(organizationId.toString())) {
            return eventDAO.findAllByOrganizationAndEndDateAfter(
                    organizationId, Calendar.getInstance().getTime());
        } else {
            throw new Exception("Organization doesn't exist");
        }
    }

    @Override
    public Set<Event> getEventsByQueries(List<String> queries) {
        return new HashSet<>(eventDAO.findAllByDescriptionContaining(queries));
    }

    @Override
    public boolean isValid(Event event) {
        return !(event.getName().isEmpty());
    }

    @Override
    public boolean exists(String id) {
        return eventDAO.exists(id);
    }

    @Override
    public Set<Event> getAll() {
        return new HashSet<>(eventDAO.findAll());
    }
}
