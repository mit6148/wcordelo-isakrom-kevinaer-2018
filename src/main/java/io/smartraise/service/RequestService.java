package io.smartraise.service;

import io.smartraise.model.Request;
import io.smartraise.model.accounts.Administrator;
import io.smartraise.model.fundraise.Charity;

import java.util.List;

public interface RequestService {

    Request makeRequest(Request.RequestType type, Charity charity);

    Request makeRequest(Request.RequestType type, Administrator administrator);

    List<Request> getRequests(String admin);

    Request getRequest(String id);

    void respond(String admin, String request, boolean response);
}
