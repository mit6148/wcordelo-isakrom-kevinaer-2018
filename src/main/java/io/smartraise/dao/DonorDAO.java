package io.smartraise.dao;

import io.smartraise.model.accounts.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonorDAO extends MongoRepository<Donor, String> {
}
