package io.smartraise.service;

import io.smartraise.model.donations.Donation;
import io.smartraise.service.skeleton.ItemService;

import java.util.List;
import java.util.UUID;

public interface DonationService extends ItemService<Donation> {

    List<Donation> getDonationsByOrganization(UUID organizationId) throws Exception;

    List<Donation> getDonationsByEvent(UUID eventId) throws Exception;

    List<Donation> getDonationsByDonor(String donorId) throws Exception;
}
