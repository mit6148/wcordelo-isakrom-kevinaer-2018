package io.smartraise.dao;

import io.smartraise.model.fundraise.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDAO extends MongoRepository<Event, String> {
}
