package io.smartraise.dao;

import io.smartraise.model.fundraise.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface OrganizationDAO extends MongoRepository<Organization, String> {

    List<Organization> findAllByOrganizationIdIn(Set<String> organizationIds);

    List<Organization> findAll();
}
