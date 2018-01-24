package io.smartraise.dao;

import io.smartraise.model.accounts.ContactInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactInformationDAO extends MongoRepository<ContactInformation, String> {
}
