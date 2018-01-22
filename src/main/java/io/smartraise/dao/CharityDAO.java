package io.smartraise.dao;

import io.smartraise.model.Privilege;
import io.smartraise.model.accounts.Charity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface CharityDAO extends MongoRepository<Charity, String> {

    List<Charity> findAllByNameContaining(List<String> queries);

    List<Charity> findAllByPrivilegeIs(Privilege privilege);
}
