package io.smartraise.model.accounts;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Payment {

    @Id
    private String username;
    private String number;
    private String type;
    private String expirationMonth;
    private String expirationYear;
    private String name;
    private String zipCode;
    private String cvv;

    public Payment() {
        this.username = UUID.randomUUID().toString();
//        this.number = 4117444444444444l;
//        this.type = "VISA";
//        this.name = "";
//        this.zipCode = "02139";
//        this.expirationMonth ="12/30";
//        this.cvv = "345";
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
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

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
