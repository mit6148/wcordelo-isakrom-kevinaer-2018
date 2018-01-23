package io.smartraise.dao;

import io.smartraise.model.fundraise.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventDAO extends MongoRepository<Event, String> {

    List<Event> findAllByOrganization(String organizationId);

    List<Event> findAllByOrganizationAndEndDateAfter(String organizationId, Date date);

    List<Event> findAllByDescriptionContaining(List<String> queries);
}
