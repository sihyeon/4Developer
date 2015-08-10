package com.project4D.fdpay.model;

import android.graphics.drawable.Drawable;

public class CardModel {
    private String text;
    private Drawable img;

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
