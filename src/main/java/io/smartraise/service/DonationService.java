package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Donation;
import io.smartraise.model.fundraise.Event;
import io.smartraise.service.skeleton.CrudService;
import io.smartraise.service.skeleton.ValidationService;

import java.util.List;

/**
 * Service for the {@link Donation} model
 */
public interface DonationService extends CrudService<Donation>, ValidationService<Donation> {

    /**
     * Obtains all donation collected by an organization
     *
     * @param organizationId    the id of an organization
     * @return a list of {@link Donation} collected by the organization
     */
    List<Donation> getDonationsByOrganization(String organizationId);

    /**
     * Obtains all donations collected by a fundraising event
     *
     * @param eventId   the id of the fundraising event
     * @return a list of {@link Donation} collected by the donation
     */
    List<Donation> getDonationsByEvent(String eventId);

    List<Donation> getDonationsByDonor(String donorId);

    boolean makeDonation(Event event, Member member);
}
