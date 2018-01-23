package io.smartraise.dao;

import io.smartraise.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface RequestDAO extends MongoRepository<Request, String> {

    List<Request> findAllByIdNotInOrderByDate(Set<String> id);
}
