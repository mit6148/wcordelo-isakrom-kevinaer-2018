package io.smartraise.dao;

import io.smartraise.model.donations.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DonationDAO extends MongoRepository<Donation, UUID> {

    List<Donation> findAllByEvent_EventId(UUID id);

    List<Donation> findAllByEvent_Organization_OrganizationId(UUID id);

    List<Donation> findAllByDonor_Username(String id);
}
