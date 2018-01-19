package io.smartraise.dao;

import io.smartraise.model.accounts.Administrator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminDAO extends MongoRepository<Administrator, String> {
}
