package io.smartraise.service.impl;

import io.smartraise.dao.RequestDAO;
import io.smartraise.dao.ResponseDAO;
import io.smartraise.model.Request;
import io.smartraise.model.Response;
import io.smartraise.model.accounts.Administrator;
import io.smartraise.model.accounts.Charity;
import io.smartraise.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private ResponseDAO responseDAO;

    @Override
    public Request makeRequest(Request.RequestType type, Charity charity) {
        Request request = new Request(type, charity);
        requestDAO.save(request);
        return request;
    }

    @Override
    public Request makeRequest(Request.RequestType type, Administrator administrator) {
        Request request = new Request(type, administrator);
        requestDAO.save(request);
        return request;
    }

    @Override
    public List<Request> getRequests(String admin) {
        Set<Response> responses = responseDAO.findAllByAdminResponseId(admin);
        Set<String> requestIds = responses.stream().map(new Function<Response, String>() {
            @Override
            public String apply(Response response) {
                return response.getRequestId();
            }
        }).collect(Collectors.toSet());
        return requestDAO.findAllByIdNotInOrderByDate(requestIds);
    }

    @Override
    public Request getRequest(String id) {
        return requestDAO.findOne(id);
    }

    @Override
    public void respond(String admin, String request, boolean bool) {
        if (!responseDAO.existsByAdminResponseIdAndRequestId(admin, request)) {
            Response response = new Response(request, admin, bool);
            responseDAO.save(response);
            if (responseDAO.countAllByRequestId(request) > 1) {
                System.out.println("Congrats");
                responseDAO.deleteAllByRequestId(request);
                requestDAO.delete(request);
            }
        }

    }
}
