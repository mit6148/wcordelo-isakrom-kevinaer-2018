package io.smartraise.model.donations;

import org.springframework.data.annotation.Id;

public class Payment {

    @Id
    private String username;
    private long cardNumber;
    private String cardType;
    private String nameOnCard;
    private String zip;
    private int cvv;

    public Payment() {
        this.username = "";
        this.cardNumber = 0;
        this.cardType = "";
        this.nameOnCard = "";
        this.zip = "";
        this.cvv = 0;
    }

    public String getUsername() {
        return username;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getZip() {
        return zip;
    }

    public int getCvv() {
        return cvv;
    }
}
