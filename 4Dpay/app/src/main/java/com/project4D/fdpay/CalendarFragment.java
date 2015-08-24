package com.project4D.fdpay;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.project4D.fdpay.adapter.CalendarListAdapter;

import java.util.Calendar;

/**
 * Created by Jaeung on 2015-08-05.
 */
public class CalendarFragment extends Fragment implements View.OnClickListener {

    private CalendarView calendar;
    private Calendar setCal = Calendar.getInstance();   //Calendar는 추상클래스라 객체를 가져와야함
    private CalendarListAdapter calendarListAdapter = null;
    private ListView calendarListView = null;


    private int setYear;
    private int setMonth;
    private int setDay;

    public static Context calendarContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View calendarView = inflater.inflate(R.layout.fragment_calendar_view, container, false);
        calendar = (CalendarView) calendarView.findViewById(R.id.calendarView);

        calendarContext = getActivity();
        //액션바 이름 변경
        setActivityTitle("월별 보기");

        //옆에 주 숫자 안보이게하기
        calendar.setShowWeekNumber(false);

        //버튼 등록 및 리스너 등록
        Button leftMonthButton = (Button) calendarView.findViewById(R.id.leftButton);
        leftMonthButton.setOnClickListener(this);
        Button rightMonthButton = (Button) calendarView.findViewById(R.id.rightButton);
        rightMonthButton.setOnClickListener(this);

        //현재 날짜
        setYear = Date.TODAY_YEAR;
        setMonth = Date.TODAY_Month;
        setDay = Date.TODAY_DAY;

        setMinMaxDate();    //캘린더 맨처음 날짜와 맨 끝 날짜 설정

        //날짜 선택이 변경될때마다 불리는 리스너
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub
                //         setYear = year;
                //         setMonth = month;
                //         setDay = dayOfMonth;
            }
        });

        //리스트 부분
        //어댑터 생성
        calendarListAdapter = new CalendarListAdapter();
        //리스트뷰 연결
        calendarListView = (ListView) calendarView.findViewById(R.id.dayHouseHold);
        //어댑터 연결
        calendarListView.setAdapter(calendarListAdapter);

        //어댑터 추가
        calendarListAdapter.add("오천원");
        calendarListAdapter.add("육천원");
        calendarListAdapter.add("칠천원");
        calendarListAdapter.add("팔천원");
        return calendarView;
    }

    //달력 몇일부터 몇일까지 할건지 정하기
    public void setMinMaxDate() {
        String closeingDay = null;

        //현재 설정되어있는 날짜
        int nowYear = Integer.parseInt(Date.DATE_FORMAT.format(calendar.getDate()).substring(0, 4));
        int nowMonth = Integer.parseInt(Date.DATE_FORMAT.format(calendar.getDate()).substring(4, 6));

        //setYear, setMonth는 설정된 날짜. 즉, 변경될 날짜

        if (nowYear < setYear) {
            //maxdate부터 설정
            //이 달의 마지막 날 가져오기
            closeingDay = Integer.toString(setCal.getActualMaximum(setCal.DAY_OF_MONTH));
            //년 월의 마지막날로 지정
            setCal.set(setYear, setMonth - 1, Integer.parseInt(closeingDay));
            //마지막 날짜 지정
            calendar.setMaxDate(setCal.getTimeInMillis());

            //년 월의 1일로 설정
            setCal.set(setYear, setMonth - 1, 1);
            //처음 날짜 지정
            calendar.setMinDate(setCal.getTimeInMillis());
        } else if (nowYear > setYear) {
            //mindate부터 설정
            setCal.set(setYear, setMonth - 1, 1);
            calendar.setMinDate(setCal.getTimeInMillis());

            closeingDay = Integer.toString(setCal.getActualMaximum(setCal.DAY_OF_MONTH));
            setCal.set(setYear, setMonth - 1, Integer.parseInt(closeingDay));
            calendar.setMaxDate(setCal.getTimeInMillis());
        } else {
            if (nowMonth < setMonth) {
                //maxdate부터 설정
                closeingDay = Integer.toString(setCal.getActualMaximum(setCal.DAY_OF_MONTH));
                setCal.set(setYear, setMonth - 1, Integer.parseInt(closeingDay));
                calendar.setMaxDate(setCal.getTimeInMillis());

                setCal.set(setYear, setMonth - 1, 1);
                calendar.setMinDate(setCal.getTimeInMillis());
            } else if (nowMonth >= setMonth) {
                //mindate부터 설정
                setCal.set(setYear, setMonth - 1, 1);
                calendar.setMinDate(setCal.getTimeInMillis());

                closeingDay = Integer.toString(setCal.getActualMaximum(setCal.DAY_OF_MONTH));
                setCal.set(setYear, setMonth - 1, Integer.parseInt(closeingDay));
                calendar.setMaxDate(setCal.getTimeInMillis());
            }
        }
    }

    private void setActivityTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
        setActivityTitle("월별 보기");

        Toast t = Toast.makeText(getActivity(),
               "실행됨" + Date.TODAY_YEAR,
                Toast.LENGTH_LONG);
        t.show();

        if (((MainActivity) MainActivity.mainActivityContext).date.useState){
            setYear = ((MainActivity) MainActivity.mainActivityContext).date.year;
            setMonth = ((MainActivity) MainActivity.mainActivityContext).date.month;
            ((MainActivity) MainActivity.mainActivityContext).date.useState = false;
            setMinMaxDate();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftButton:
                if (setMonth != 1) {
                    setMonth--;
                } else if (setMonth == 1) {
                    setMonth = 12;
                    setYear--;
                }
                setMinMaxDate();
                break;
            case R.id.rightButton:
                if (setMonth != 12) {
                    setMonth++;
                } else if (setMonth == 12) {
                    setMonth = 1;
                    setYear++;
                }
                setMinMaxDate();
                break;
        }
    }
}
