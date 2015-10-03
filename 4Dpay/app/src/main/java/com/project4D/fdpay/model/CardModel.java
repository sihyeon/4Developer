package com.project4D.fdpay.model;

/**
 * This is card Model, controls image and name that shows in activity
 */
public class CardModel {
    private String text;
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
