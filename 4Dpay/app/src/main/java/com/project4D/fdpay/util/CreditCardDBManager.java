package com.project4D.fdpay.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project4D.fdpay.model.CardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Somin Lee (sayyo1120@gmail.com)
 */

public class CreditCardDBManager extends SQLiteOpenHelper {
    private static final String TABLENAME = "CARD";

    private CreditCardDBManager(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
    }
    public static CreditCardDBManager newCreditCardDBManager(Context context){
        return new CreditCardDBManager(context, "DATABASE 1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createCard = "CREATE TABLE CARD ( " +
                "ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "NUMBER CHAR(16), " +
                "NAME CHAR(30) NOT NULL)";
        db.execSQL(createCard);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CARD");
        onCreate(db);
    }

    public void add(CardInfo card){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUMBER", card.getCardNum());
        contentValues.put("NAME", card.getCardName());
        db.insert("CARD", null, contentValues);
    }

    public void close(){
        this.close();
    }

    public List<CardInfo> getAll(){
        List<CardInfo> contactList = new ArrayList<CardInfo>();
        String selectQuery = "SELECT * FROM CARD";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CardInfo card = new CardInfo();
                card.setCardNum(cursor.getString(1));
                Log.i("TAG", "getAll 1" + cursor.getString(1));
                card.setCardName(cursor.getString(2));
                Log.i("TAG", "getAll 2" + cursor.getString(2));
                contactList.add(card);
                } while (cursor.moveToNext());
            }
        cursor.close();
        return contactList;
    }

    public List<String> getAllName(){
        List<String> contactList = new ArrayList<String>();
        String selectQuery = "SELECT NAME FROM CARD";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                contactList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public int getCount(){
        return getAll().size();
    }

    public String getName(int position){
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "SELECT NAME FROM CARD WHERE ID = "+position;
        Cursor cursor = db.rawQuery(selectQuery, null);
        String result = cursor.getString(0);
        cursor.close();
        return result;
    }

    public void deleteAll(){

    }
    //TODO { http://mainia.tistory.com/670 }
}
