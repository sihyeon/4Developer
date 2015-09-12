package com.project4D.fdpay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015-08-24.
 */
public class CreditCardInfo {
    String cardNum;
    int cardValid;
    int password;
    String cardName;
    int cvc;

    public CreditCardInfo(){}

    public CreditCardInfo(String cardNum, int cardValid, int password, String cardName, int cvc) {
        this.cardNum = cardNum;
        this.cardValid = cardValid;
        this.password = password;
        this.cardName = cardName;
        this.cvc = cvc;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
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
