package io.smartraise.service.impl;

import io.smartraise.dao.DonationDAO;
import io.smartraise.model.donations.Donation;
import io.smartraise.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationDAO donationDAO;

    @Override
    public List<Donation> getDonationsByOrganization(String organizationId) throws Exception {
        return donationDAO.findAllByOrganization(organizationId);
    }

    @Override
    public List<Donation> getDonationsByEvent(String eventId) throws Exception {
        return donationDAO.findAllByEvent(eventId);
    }

    @Override
    public List<Donation> getDonationsByDonor(String donorId) throws Exception {
        return donationDAO.findAllByDonor(donorId);
    }

    @Override
    public Donation get(String id) throws Exception {
        return donationDAO.findOne(id);
    }

    @Override
    public Donation create(Donation donation) throws Exception {
        if (isValid(donation) && !donationDAO.exists(donation.getDonationId())) {
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
    public void delete(String id) throws Exception {
        if (donationDAO.exists(id)) {
            donationDAO.delete(id);
        }
    }

    @Override
    public boolean isValid(Donation donation) {
        return true;
    }

    @Override
    public boolean exists(String id) {
        return donationDAO.exists(id);
    }
}
