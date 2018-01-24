package io.smartraise.service.impl;

import io.smartraise.dao.AdminDAO;
import io.smartraise.util.Parser;
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
        if (!this.isValid(admin)) {
            throw new Exception("Fields empty");
        }
        if (adminDAO.exists(admin.getUsername())) {
            throw new Exception("Member already exists");
        } else {
            adminDAO.save(admin);
        }
    }

    @Override
    public void update(Administrator admin) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public boolean isValid(Administrator administrator) {
        return administrator.getEmail() != "" && administrator.getUsername() != "";
    }

    @Override
    public boolean exists(String id) {
        return adminDAO.exists(id);
    }

    @Override
    public long count() {
        return adminDAO.count();
    }
}
