package com.project4D.fdpay.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project4D.fdpay.model.CardInfo;

/**
 * @Author Somin Lee (sayyo1120@gmail.com)
 */

public class CardDBManager extends SQLiteOpenHelper {
    public CardDBManager(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCard = "create CARD ( " +
                "cardNum INTEGER PRIMARY KEY, " +
                "cardValid INTEGER, " +
                "password INTEGER, " +
                "cardName TEXT, " +
                "cvc INTEGER )";
        db.execSQL(createCard);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CARD");
        onCreate(db);
    }

    public void add(CardInfo card){
        //DO IT NEED?
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardNum", card.getCardNum());
        contentValues.put("cardValid", card.getCardValid());
        contentValues.put("password", card.getPassword());
        contentValues.put("cardName", card.getCardName());
        contentValues.put("cvc", card.getCvc());
        database.insert("CARD", null, contentValues);
    }

    //TODO { http://mainia.tistory.com/670 }
}
