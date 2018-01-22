package io.smartraise.service.impl;

import io.smartraise.dao.DonorDAO;
import io.smartraise.model.accounts.Donor;
import io.smartraise.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;

public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorDAO donorDAO;

    @Override
    public Donor get(String id) throws Exception {
        return donorDAO.findOne(id);
    }

    @Override
    public void create(Donor donor) throws Exception {
        if (isValid(donor) && !donorDAO.exists(donor.getUsername())) {
            donorDAO.save(donor);
        } else {
            throw new Exception("Donor already exists");
        }
    }

    @Override
    public void update(Donor donor) throws Exception {
        if (donorDAO.exists(donor.getUsername())) {
            donorDAO.save(donor);
        } else {
            throw new Exception("No donor found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        if (donorDAO.exists(id)) {
            donorDAO.delete(id);
        } else {
            throw new Exception("No donor found");
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
