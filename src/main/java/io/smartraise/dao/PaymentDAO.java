package io.smartraise.dao;

import io.smartraise.model.accounts.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentDAO extends MongoRepository<Payment, String> {

    void deleteAllByUsername(String username);
}
