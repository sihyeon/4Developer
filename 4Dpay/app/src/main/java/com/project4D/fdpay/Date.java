package com.project4D.fdpay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jaeung on 2015-08-18.
 */
public class Date {
    //데이트 형식을 설정
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    //현재 날짜 설정
    public static final int TODAY_YEAR = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(0, 4));
    public static final int TODAY_Month = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(4, 6));
    public static final int TODAY_DAY = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(6, 8));

    public static int year;
    public static int month;
    public static int day;
    //이 날짜를 쓰는지 아닌지 상태설정
    public static boolean useState;
    public Date(){
    }
    //test
}
