package com.project4D.fdpay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jaeung on 2015-08-18.
 */
public class Date {
    //��¥�� ������ ���˹��
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    //���� ��¥
    public static final int TODAY_YEAR = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(0, 4));
    public static final int TODAY_Month = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(4, 6));
    public static final int TODAY_DAY = Integer.parseInt(Date.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(6, 8));

    public static int year;
    public static int month;
    public static int day;
    //����� ������
    public static boolean useState;
    public Date(){
    }

}
