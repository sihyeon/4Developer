package com.project4D.fdpay.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * @version 2015-09-08.
 * The upper class about mysql - make database
 */
public class DBManager extends SQLiteOpenHelper {
    public static String DATABASENAME;
    private static final String CREATE_CREDIT = "CREATE TABLE CREDIT ( " +
            "_id INTEGER PRIMARY KEY, " +
            "NUMBER CHAR(16), " +
            "NAME CHAR(30) NOT NULL)";
    private static final String CREATE_POINT = "CREATE TABLE POINT ( " +
            "_id INTEGER PRIMARY KEY, " +
            "NAME CHAR(30) NOT NULL)";

    private static final String DROP_CREDIT = "DROP TABLE IF EXISTS CREDIT";
    private static final String DROP_POINT = "DROP TABLE IF EXISTS POINT";


    public DBManager(Context context){
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CREDIT);
        db.execSQL(CREATE_POINT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CREDIT);
        db.execSQL(DROP_POINT);
        onCreate(db);
    }

    public static void setDbName(String dbname){
        DATABASENAME = dbname;
    }
}
