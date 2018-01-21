package io.smartraise.service.impl;

import io.smartraise.dao.DonationDAO;
import io.smartraise.model.donations.Donation;
import io.smartraise.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationDAO donationDAO;

    @Override
    public List<Donation> getDonationsByOrganization(UUID organizationId) throws Exception {
        return donationDAO.findAllByEvent_Organization_OrganizationId(organizationId);
    }

    @Override
    public List<Donation> getDonationsByEvent(UUID eventId) throws Exception {
        return donationDAO.findAllByEvent_EventId(eventId);
    }

    @Override
    public List<Donation> getDonationsByDonor(String donorId) throws Exception {
        return donationDAO.findAllByDonor_Username(donorId);
    }

    @Override
    public Donation get(UUID id) throws Exception {
        return donationDAO.findOne(id);
    }

    @Override
    public Donation create(Donation donation) throws Exception {
        if (isValid(donation) && donationDAO.exists(donation.getDonationId())) {
            donationDAO.save(donation);
            return donation;
        } else {
            throw new Exception("Invalid donation");
        }
    }

    @Override
    public void update(Donation donation) throws Exception {
        if (isValid(donation)) {
            donationDAO.save(donation);
        }
    }

    @Override
    public void delete(UUID id) throws Exception {
        if (donationDAO.exists(id)) {
            donationDAO.delete(id);
        }
    }

    @Override
    public boolean isValid(Donation donation) {
        return true;
    }
}
