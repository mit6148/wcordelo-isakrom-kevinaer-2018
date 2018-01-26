package io.smartraise.service.deprecated;

import io.smartraise.model.accounts.Administrator;
import io.smartraise.service.skeleton.AccountCrudService;
import io.smartraise.service.skeleton.ValidationService;

@Deprecated
public interface AdminService extends AccountCrudService<Administrator>, ValidationService<Administrator> {

    long count();
}
