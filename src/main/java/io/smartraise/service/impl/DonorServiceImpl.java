package io.smartraise.service.impl;

import io.smartraise.dao.DonorDAO;
import io.smartraise.dao.PaymentDAO;
import io.smartraise.model.accounts.Donor;
import io.smartraise.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;

public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorDAO donorDAO;

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public Donor get(String id) {
        return donorDAO.findOne(id);
    }

    @Override
    public boolean create(Donor donor) {
        if (isValid(donor) && !donorDAO.exists(donor.getUsername())) {
            donorDAO.save(donor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Donor donor) {
        if (exists(donor.getUsername()) && isValid(donor)) {
            donorDAO.save(donor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Donor donor = this.get(id);
        if (donorDAO.exists(id)) {
            paymentDAO.delete(donor.getPayment());
            donorDAO.delete(donor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(Donor donor) {
        return !(donor.getUsername().isEmpty() && donor.getEmail().isEmpty());
    }

    @Override
    public boolean exists(String id) {
        return donorDAO.exists(id);
    }
}
