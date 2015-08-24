package com.project4D.fdpay.model;

/**
 * Created by Administrator on 2015-08-24.
 */
public class CardInfo {
    int cardNum;
    int cardValid;
    int password;
    String cardName;
    int cvc;

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getCardName() {

        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardValid() {

        return cardValid;
    }

    public void setCardValid(int cardValid) {
        this.cardValid = cardValid;
    }

    public int getPassword() {

        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
