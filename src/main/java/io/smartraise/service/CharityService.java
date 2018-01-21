package io.smartraise.service;

import io.smartraise.model.accounts.Charity;
import io.smartraise.service.skeleton.AccountService;
import io.smartraise.service.skeleton.ItemService;

import java.util.List;
import java.util.Set;

public interface CharityService extends ItemService<Charity> {

    Set<Charity> getCharities(List<String> terms);
}
