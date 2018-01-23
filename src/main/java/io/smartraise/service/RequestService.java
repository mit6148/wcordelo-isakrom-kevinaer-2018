package io.smartraise.service;

import io.smartraise.model.Request;
import io.smartraise.model.accounts.Administrator;

import java.util.List;

public interface RequestService {

    List<Request> getRequests(Administrator administrator);

    Request getRequest(String id);

    void respond(Administrator administrator, Request request);
}
