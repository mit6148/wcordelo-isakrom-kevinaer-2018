package io.smartraise.dao;

import io.smartraise.model.fundraise.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventDAO extends MongoRepository<Event, UUID> {

    List<Event> findAllByOrganization_OrganizationId(UUID organizationId);

    List<Event> findAllByOrganization_OrganizationIdAndEndDateAfter(UUID organizationId, Date date);

    List<Event> findAllByDescriptionContaining(List<String> queries);
}
