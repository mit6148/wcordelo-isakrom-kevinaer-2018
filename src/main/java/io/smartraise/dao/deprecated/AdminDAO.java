package io.smartraise.dao.deprecated;

import io.smartraise.model.accounts.Administrator;
import org.springframework.data.mongodb.repository.MongoRepository;

@Deprecated
public interface AdminDAO extends MongoRepository<Administrator, String> {

    Administrator findByEmail(String email);
}
