package io.smartraise.dao;

import io.smartraise.model.donations.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonationDAO extends MongoRepository<Donation, String> {
}
