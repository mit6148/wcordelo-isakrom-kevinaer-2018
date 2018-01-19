package io.smartraise.dao;

import io.smartraise.model.accounts.login.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CredentialDAO extends MongoRepository<Credential, String> {

    Credential findByEmail(String email);
}
