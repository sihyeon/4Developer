package com.project4D.fdpay.internal;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jaeung on 2015-08-18.
 */

//적은 양의 데이터를 넘기기 위한 클래스
public class HouseHolderStatus {
    //DATE
    //데이트 형식을 설정
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    //현재 날짜 설정
    public static final int TODAY_YEAR = Integer.parseInt(HouseHolderStatus.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(0, 4));
    public static final int TODAY_Month = Integer.parseInt(HouseHolderStatus.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(4, 6));
    public static final int TODAY_DAY = Integer.parseInt(HouseHolderStatus.DATE_FORMAT.format(Calendar.getInstance().getTimeInMillis()).substring(6, 8));

    public static int year = TODAY_YEAR;
    public static int month = TODAY_Month;
    public static int day = TODAY_DAY;
    //이 날짜를 쓰는지 아닌지 상태설정
    public static boolean dateUseStatus = false;


    //WRITING_INFO
    public static String breakdown = null;
    public static String categorization = null;
    public static int amount = 0;
    public static boolean writingInfoUseStatus = false;

    public static String categorizationText = null;
    public static boolean categorizationTextStatus = false;

    public HouseHolderStatus(){
    }
    //test slack
}
