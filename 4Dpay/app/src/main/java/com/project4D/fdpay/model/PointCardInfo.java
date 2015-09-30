package com.project4D.fdpay.model;

/**
 * @Author Somin Lee (sayyo1120@gmail.com)
 * @version 2015-09-07.
 */
public class PointCardInfo {
    String cardName;

    public PointCardInfo(){}

    public PointCardInfo(String cardName){ this.cardName = cardName; }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
