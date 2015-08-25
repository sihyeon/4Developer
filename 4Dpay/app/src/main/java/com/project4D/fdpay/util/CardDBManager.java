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
                "TYPE TEXT, "+
                "NUMBER INTEGER PRIMARY KEY, " +
                "VALID INTEGER, " +
                "PASSWORD INTEGER, " +
                "NAME TEXT, " +
                "CVC INTEGER )";
        db.execSQL(createCard);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CARD");
        onCreate(db);
    }

    public void add(Context context, CardInfo card){
        //DO IT NEED?
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TYPE", context.getClass().toString());
        contentValues.put("NUMBER", card.getCardNum());
        contentValues.put("VALID", card.getCardValid());
        contentValues.put("PASSWORD", card.getPassword());
        contentValues.put("NAME", card.getCardName());
        contentValues.put("CVC", card.getCvc());
        db.insert("CARD", null, contentValues);
    }

    //TODO { http://mainia.tistory.com/670 }
}
