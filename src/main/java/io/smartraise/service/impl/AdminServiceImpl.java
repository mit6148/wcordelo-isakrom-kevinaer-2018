package io.smartraise.service.impl;

import io.smartraise.dao.AdminDAO;
import io.smartraise.helper.Parser;
import io.smartraise.model.accounts.Administrator;
import io.smartraise.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Administrator get(String id) throws Exception {
        if (Parser.isEmail(id)) {
            return adminDAO.findByEmail(id);
        } else {
            return adminDAO.findOne(id);
        }
    }

    @Override
    public void create(Administrator admin) throws Exception {
        if (admin.getEmail() == "") {
            throw new Exception("Fields empty");
        }
        if (adminDAO.exists(admin.getEmail())) {
            throw new Exception("Member already exists");
        } else {
            adminDAO.save(admin);
        }
    }

    @Override
    public void update(Administrator admin) throws Exception {

    }

    @Override
    public void delete(Administrator admin) throws Exception {

    }
}
