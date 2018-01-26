package io.smartraise.service.impl;

import io.smartraise.dao.DonationDAO;
import io.smartraise.model.fundraise.Donation;
import io.smartraise.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationDAO donationDAO;

    @Override
    public List<Donation> getDonationsByOrganization(String organizationId) {
        return donationDAO.findAllByOrganization(organizationId);
    }

    @Override
    public List<Donation> getDonationsByEvent(String eventId) {
        return donationDAO.findAllByEvent(eventId);
    }

    @Override
    public List<Donation> getDonationsByDonor(String donorId) {
        return donationDAO.findAllByDonor(donorId);
    }

    @Override
    public Donation get(String id) {
        return donationDAO.findOne(id);
    }

    @Override
    public boolean create(Donation donation) {
        if (isValid(donation) && !donationDAO.exists(donation.getDonationId())) {
            donationDAO.save(donation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Donation donation) {
        if (isValid(donation) && exists(donation.getDonationId())) {
            donationDAO.save(donation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (donationDAO.exists(id)) {
            donationDAO.delete(id);
            return true;
        } else {
            return false;
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
