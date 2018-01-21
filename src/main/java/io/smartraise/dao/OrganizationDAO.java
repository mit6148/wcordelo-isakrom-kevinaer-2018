package io.smartraise.dao;

import io.smartraise.model.fundraise.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrganizationDAO extends MongoRepository<Organization, UUID> {
}
