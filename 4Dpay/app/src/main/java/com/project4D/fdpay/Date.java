package com.project4D.fdpay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jaeung on 2015-08-18.
 */
public class Date {
    //날짜의 데이터 포맷방식
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    //현재 날짜
    public static final int TODAY_YEAR = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(0, 4));
    public static final int TODAY_Month = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(4, 6));
    public static final int TODAY_DAY = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(6, 8));

    private static int year;
    private static int month;
    private static int day;
    //사용할 것인지
    private static boolean useState;
    public Date(){
    }
}
