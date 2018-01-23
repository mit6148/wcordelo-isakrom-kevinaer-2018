package io.smartraise.model;

import io.smartraise.model.accounts.Administrator;
import io.smartraise.model.accounts.Charity;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Request {

    public enum RequestType{VERIFY_ADMIN, VERIFY_CHARITY, REMOVE_CHARITY, REMOVE_ADMIN}

    @Id
    private final String id;
    private final RequestType type;
    private final String administrator;
    private final String charity;
    private final Date date;

    public Request() {
        this.id = UUID.randomUUID().toString();
        this.type = null;
        this.administrator = "";
        this.charity = "";
        this.date = Calendar.getInstance().getTime();
    }

    public Request(RequestType type, Administrator administrator) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.administrator = administrator.getUsername();
        this.charity = "";
        this.date = Calendar.getInstance().getTime();
    }

    public Request(RequestType type, Charity charity) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.administrator = "";
        this.charity = charity.getCharityId();
        this.date = Calendar.getInstance().getTime();
    }

    public String getId() { return id; }

    public RequestType getType() {
        return type;
    }

    public String getAdministrator() {
        return administrator;
    }

    public String getCharity() {
        return charity;
    }

    public Date getDate() {
        return date;
    }

}
