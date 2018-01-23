package io.smartraise.service;

import io.smartraise.model.donations.Donation;
import io.smartraise.service.skeleton.ItemService;

import java.util.List;
import java.util.UUID;

/**
 * Service for the {@link Donation} model
 */
public interface DonationService extends ItemService<Donation> {

    /**
     * Obtains all donation collected by an organization
     *
     * @param organizationId    the id of an organization
     * @return a list of {@link Donation} collected by the organization
     * @throws Exception
     */
    List<Donation> getDonationsByOrganization(String organizationId) throws Exception;

    /**
     * Obtains all donations collected by a fundraising event
     *
     * @param eventId   the id of the fundraising event
     * @return a list of {@link Donation} collected by the donation
     * @throws Exception
     */
    List<Donation> getDonationsByEvent(String eventId) throws Exception;

    List<Donation> getDonationsByDonor(String donorId) throws Exception;
}
