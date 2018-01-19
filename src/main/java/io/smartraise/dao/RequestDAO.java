package io.smartraise.dao;

import io.smartraise.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestDAO extends MongoRepository<Request, String> {
}
