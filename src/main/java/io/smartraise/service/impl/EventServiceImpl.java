package io.smartraise.service.impl;

import io.smartraise.dao.EventDAO;
import io.smartraise.dao.OrganizationDAO;
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
    public Event get(String id) {
        return eventDAO.findOne(id);
    }

    @Override
    public boolean create(Event event) {
        if (isValid(event) && !eventDAO.exists(event.getEventId())) {
            eventDAO.save(event);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Event event) {
        if (this.exists(event.getEventId()) && this.isValid(event)) {
            eventDAO.save(event);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (eventDAO.exists(id)) {
            eventDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Event> getEventsFromOrganization(String organizationId) {
        return eventDAO.findAllByOrganization_OrganizationId(organizationId);
    }

    @Override
    public List<Event> getCurrentEventsFromOrganization(String organizationId) {
        return eventDAO.findAllByOrganizationAndEndDateAfter(
                organizationId, Calendar.getInstance().getTime());
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

    @Override
    public List<Event> getCurrentEvents() {
        Date date = Calendar.getInstance().getTime();
        return eventDAO.findAllByStartDateBeforeAndEndDateAfterOrderByStartDate(date, date);
    }

    @Override
    public List<Event> getExpiredEvents() {
        Date date = Calendar.getInstance().getTime();
        return eventDAO.findAllByEndDateAfterOrderByEndDate(date);
    }

    @Override
    public List<Event> getFutureEvents() {
        Date date = Calendar.getInstance().getTime();
        return eventDAO.findAllByStartDateAfterOrderByStartDate(date);
    }
}
