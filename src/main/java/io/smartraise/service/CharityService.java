package io.smartraise.service;

import io.smartraise.model.accounts.Charity;

import java.util.List;

public interface CharityService extends Service<Charity> {

    List<Charity> getCharities(List<String> terms);
}
