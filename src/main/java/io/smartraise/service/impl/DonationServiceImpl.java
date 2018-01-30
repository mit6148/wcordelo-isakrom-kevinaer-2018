package io.smartraise.service.impl;

import io.smartraise.dao.*;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Donation;
import io.smartraise.model.fundraise.Event;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationDAO donationDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private CharityDAO charityDAO;

    @Override
    public List<Donation> getDonationsByOrganization(String organizationId) {
        return donationDAO.findAllByOrganization_OrganizationId(organizationId);
    }

    @Override
    public List<Donation> getDonationsByEvent(String eventId) {
        return donationDAO.findAllByEvent(eventId);
    }

    @Override
    public List<Donation> getDonationsByDonor(String donorId) {
        return donationDAO.findAllByDonor_Username(donorId);
    }

    @Override
    public Donation get(String id) {
        return donationDAO.findOne(id);
    }

    @Override
    public boolean create(Donation donation) {
        if (isValid(donation) && !donationDAO.exists(donation.getDonationId())) {
            double amount = donation.getAmount();
            donationDAO.save(donation);
            donation.getEvent().setDonation(donation.getEvent().getDonation() + amount);
            eventDAO.save(donation.getEvent());
            donation.getDonor().setDonation(donation.getDonor().getDonation() + amount);
            memberDAO.save(donation.getDonor());
            donation.getCharity().setDonation(donation.getCharity().getDonation() + amount);
            charityDAO.save(donation.getCharity());
            donation.getOrganization().setDonation(donation.getOrganization().getDonation() + amount);
            organizationDAO.save(donation.getOrganization());
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

    @Override
    public boolean makeDonation(Event event, Member member, Double amount) {
        Donation donation = new Donation(member, event, amount);
        return create(donation);
    }

}
