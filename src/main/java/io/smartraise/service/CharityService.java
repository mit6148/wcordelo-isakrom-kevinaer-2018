package io.smartraise.service;

import io.smartraise.model.accounts.Charity;
import io.smartraise.service.skeleton.CrudService;
import io.smartraise.service.skeleton.ValidationService;

import java.util.List;
import java.util.Set;

public interface CharityService extends CrudService<Charity>, ValidationService<Charity> {

    Set<Charity> getCharities(List<String> terms);

    Set<Charity> getAll();
}
