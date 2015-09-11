package com.project4D.fdpay.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project4D.fdpay.model.CreditCardInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Somin Lee (sayyo1120@gmail.com)
 */

public class CreditCardTableManager {
    private static final String TABLENAME = "CREDIT";
    private DBManager dbManager;


    public CreditCardTableManager(Context context){
        dbManager = new DBManager(context);
    }


    public int add(CreditCardInfo card) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUMBER", card.getCardNum());
        contentValues.put("NAME", card.getCardName());
        return (int) dbManager.getWritableDatabase().insert(TABLENAME, null, contentValues);
    }


    public List<CreditCardInfo> getAll() {
        List<CreditCardInfo> contactList = new ArrayList<CreditCardInfo>();
        String selectQuery = "SELECT * FROM "+TABLENAME;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CreditCardInfo card = new CreditCardInfo();
                Log.i("TAG", "getAll _id - " + cursor.getColumnName(0) + " : " + cursor.getInt(0));
                card.setCardNum(cursor.getString(1));
                Log.i("TAG", "getAll num : " + cursor.getColumnName(1) + " : " + cursor.getString(1));
                card.setCardName(cursor.getString(2));
                Log.i("TAG", "getAll name : " + cursor.getColumnName(2) + " : " + cursor.getString(2));
                contactList.add(card);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<String> getAllCardName() {
        final List<String> cardNameList = new ArrayList<>();
        String selectQuery = "SELECT NAME FROM "+TABLENAME;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                cardNameList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cardNameList;
    }


    public String getCardNameById(int id) {
        String result = null;
        String selectQuery = "SELECT NAME FROM "+TABLENAME+" WHERE _id = " + id;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;
    }

    public void updateCardNameById(String name, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        dbManager.getWritableDatabase().update(TABLENAME, contentValues, "_id=?", new String[]{String.valueOf(id)});
    }

    public void deleteCardInfoById(int id) {
        dbManager.getWritableDatabase().delete(TABLENAME, "_id=?", new String[]{String.valueOf(id)});
    }

    public List<Integer> getAllId() {
        final List<Integer> cardIdList = new ArrayList<>();
        String selectQuery = "SELECT _id FROM "+TABLENAME;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                cardIdList.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cardIdList;
    }
}
