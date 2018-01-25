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
    public Administrator get(String id){
        if (Parser.isEmail(id)) {
            return adminDAO.findByEmail(id);
        } else {
            return adminDAO.findOne(id);
        }
    }

    @Override
    public boolean create(Administrator admin) {
        if (this.isValid(admin) && !adminDAO.exists(admin.getUsername())) {
            return false;
        } else {
            adminDAO.save(admin);
            return true;
        }
    }

    @Override
    public boolean update(Administrator admin) {
        return true;
    }

    @Override
    public boolean delete(String id) {
        return true;
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
