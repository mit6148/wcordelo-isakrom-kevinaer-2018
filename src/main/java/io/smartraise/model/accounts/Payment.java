package io.smartraise.model.accounts;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Payment {

    @Id
    private String username;
    private long number;
    private String type;
    private String expiration;
    private String name;
    private String zipCode;
    private String cvv;

    public Payment() {
        this.username = UUID.randomUUID().toString();
        this.number = 0;
        this.type = "";
        this.name = "";
        this.zipCode = "";
        this.expiration ="";
        this.cvv = "";
    }

    public String getUsername() {
        return username;
    }

    public long getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiration() {
        return expiration;
    }
}
