package io.smartraise.dao;

import io.smartraise.model.accounts.Charity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharityDAO extends MongoRepository<Charity, String> {
}
