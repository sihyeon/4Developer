package com.project4D.fdpay.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project4D.fdpay.model.PointCardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Somin Lee(sayyo1120@gmail.com)
 */
public class PointCardTableManager {
    private static final String TABLENAME = "POINT";
    private DBManager dbManager;

    public PointCardTableManager(Context context){
        dbManager = new DBManager(context);
    }


    public int add(PointCardInfo card) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", card.getCardName());
        return (int) dbManager.getWritableDatabase().insert(TABLENAME, null, contentValues);
    }

    public void close() {
        this.close();
    }

    public List<PointCardInfo> getAll() {
        List<PointCardInfo> contactList = new ArrayList<PointCardInfo>();
        String selectQuery = "SELECT * FROM " + TABLENAME;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PointCardInfo card = new PointCardInfo();
                card.setCardName(cursor.getString(1));
                contactList.add(card);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<String> getAllCardName() {
        final List<String> cardNameList = new ArrayList<String>();
        String selectQuery = "SELECT NAME FROM " + TABLENAME;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                cardNameList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cardNameList;
    }


    public String getCardNameById(int position) {
        String result = null;
        String selectQuery = "SELECT NAME FROM " + TABLENAME + " WHERE _id = " + position;
        Cursor cursor = dbManager.getWritableDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;
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

    public void deleteCardInfoById(int id) {
        dbManager.getWritableDatabase().delete(TABLENAME, "_id=?", new String[]{String.valueOf(id)});
    }

}
