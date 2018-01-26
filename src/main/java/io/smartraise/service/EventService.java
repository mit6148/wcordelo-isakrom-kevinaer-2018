package io.smartraise.service;

import io.smartraise.model.fundraise.Event;
import io.smartraise.service.skeleton.CrudService;
import io.smartraise.service.skeleton.ValidationService;

import java.util.List;
import java.util.Set;

public interface EventService extends CrudService<Event>, ValidationService<Event> {

    List<Event> getEventsFromOrganization(String organizationId);

    List<Event> getCurrentEventsFromOrganization(String organizationId);

    Set<Event> getEventsByQueries(List<String> queries);

    Set<Event> getAll();

    List<Event> getCurrentEvents();

    List<Event> getExpiredEvents();

    List<Event> getFutureEvents();
}
