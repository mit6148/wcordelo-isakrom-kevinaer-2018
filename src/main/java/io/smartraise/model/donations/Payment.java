package io.smartraise.model.donations;

public class Payment {

    private final long cardNumber;
    private final String cardType;
    private final String nameOnCard;
    private final String zip;
    private final int cvv;

    public Payment() {
        this.cardNumber = 0;
        this.cardType = "";
        this.nameOnCard = "";
        this.zip = "";
        this.cvv = 0;
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
