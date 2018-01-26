package io.smartraise.dao;

import io.smartraise.model.fundraise.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DonationDAO extends MongoRepository<Donation, String> {

    List<Donation> findAllByEvent(String id);

    List<Donation> findAllByOrganization(String id);

    List<Donation> findAllByDonor(String id);

    List<Donation> findAllByCharity(String id);
}
