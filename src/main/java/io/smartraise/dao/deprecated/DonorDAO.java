package io.smartraise.dao.deprecated;

import io.smartraise.model.accounts.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;

@Deprecated
public interface DonorDAO extends MongoRepository<Donor, String> {
}
