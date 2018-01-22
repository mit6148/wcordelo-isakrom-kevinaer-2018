package io.smartraise.service.impl;

import io.smartraise.dao.CharityDAO;
import io.smartraise.model.accounts.Charity;
import io.smartraise.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityDAO charityDAO;

    @Override
    public Set<Charity> getCharities(List<String> terms) {
        return new HashSet<>(charityDAO.findAllByNameContaining(terms));
    }

    @Override
    public Charity get(String id) throws Exception {
        return charityDAO.findOne(id);
    }

    @Override
    public Charity create(Charity charity) throws Exception {
        if (isValid(charity)) {
            charityDAO.save(charity);
            return charity;
        } else {
            throw new Exception("Not a valid charity");
        }
    }

    @Override
    public void update(Charity charity) throws Exception {
        if (charityDAO.exists(charity.getCharityId())) {
            charityDAO.save(charity);
        } else {
            throw new Exception("Charity doesn't exist");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        if (charityDAO.exists(id)) {
            charityDAO.delete(id);
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
