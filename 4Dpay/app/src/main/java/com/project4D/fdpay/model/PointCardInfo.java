package com.project4D.fdpay.model;

import com.project4D.fdpay.manager.PointCardDBManager;

/**
 * Created by Administrator on 2015-09-07.
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
