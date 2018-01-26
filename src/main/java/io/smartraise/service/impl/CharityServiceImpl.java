package io.smartraise.service.impl;

import io.smartraise.dao.CharityDAO;
import io.smartraise.dao.PaymentDAO;
import io.smartraise.model.fundraise.Charity;
import io.smartraise.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityDAO charityDAO;

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public Set<Charity> getCharities(List<String> terms) {
        return new HashSet<>(charityDAO.findAllByNameContaining(terms));
    }

    @Override
    public Charity get(String id) {
        return charityDAO.findOne(id);
    }

    @Override
    public boolean create(Charity charity) {
        if (isValid(charity) && !exists(charity.getCharityId())) {
            charityDAO.save(charity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Charity charity) {
        if (charityDAO.exists(charity.getCharityId())) {
            charityDAO.save(charity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Charity charity = this.get(id);
        if (charityDAO.exists(id)) {
            paymentDAO.delete(charity.getPayment());
            charityDAO.delete(charity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(Charity charity) {
        return !charity.getName().isEmpty();
    }

    @Override
    public boolean exists(String id) {
        return charityDAO.exists(id);
    }

    @Override
    public Set<Charity> getAll() {
        return new HashSet<>(charityDAO.findAll());
    }
}
