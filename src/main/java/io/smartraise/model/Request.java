package io.smartraise.model;

import io.smartraise.model.accounts.Administrator;
import io.smartraise.model.accounts.Charity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private enum RequestType{VERIFY_ADMIN, VERIFY_CHARITY, REMOVE_CHARITY, REMOVE_ADMIN}

    private final RequestType type;
    private final Administrator administrator;
    private final Charity charity;
    private final Date date;
    private final Map<Administrator, Boolean> responses;

    public Request() {
        this.type = null;
        this.administrator = null;
        this.charity = null;
        this.date = Calendar.getInstance().getTime();
        this.responses = new HashMap<>();
    }

    public RequestType getType() {
        return type;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public Charity getCharity() {
        return charity;
    }

    public Date getDate() {
        return date;
    }

    public Map<Administrator, Boolean> getResponses() {
        return responses;
    }

    public void addResponse(Administrator administrator, boolean response){
        responses.put(administrator, response);
    }
}
