package io.smartraise.service;

import io.smartraise.model.accounts.Administrator;
import io.smartraise.service.skeleton.AccountCrudService;
import io.smartraise.service.skeleton.ValidationService;

public interface AdminService extends AccountCrudService<Administrator>, ValidationService<Administrator> {

    long count();
}
