package io.smartraise.dao;

import io.smartraise.model.Response;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface ResponseDAO extends MongoRepository<Response, String> {

    Set<Response> findAllByAdminResponseId(String id);

    int countAllByRequestId(String id);

    void deleteAllByRequestId(String id);

    boolean existsByAdminResponseIdAndRequestId(String admin, String request);
}
