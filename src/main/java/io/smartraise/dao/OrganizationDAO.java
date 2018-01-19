package io.smartraise.dao;

import io.smartraise.model.fundraise.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationDAO extends MongoRepository<Organization, String> {
}
