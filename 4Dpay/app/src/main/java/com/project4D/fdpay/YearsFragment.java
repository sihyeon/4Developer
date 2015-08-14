package com.project4D.fdpay;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project4D.fdpay.adapter.YearsListAdapter;

/**
 * Created by Jaeung on 2015-07-28.
 */
public class YearsFragment extends Fragment implements OnClickListener {
    private ListView mListView = null;
    private YearsListAdapter yearsListAdapter = null;
    private TextView listYears;
    public static Context yearsContext;

//    private int listYearsNumber = ((CalendarActivity)CalendarActivity.calendarContext).getSetYear();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View yearsView = inflater.inflate(R.layout.years_view, null);

        //어댑터 생성
        yearsListAdapter = new YearsListAdapter();
        //리스트뷰 연결
        mListView = (ListView) yearsView.findViewById(R.id.listView);
        //어댑터 연결
        mListView.setAdapter(yearsListAdapter);

        yearsListAdapter.add("12");
        yearsListAdapter.add("11");
        yearsListAdapter.add("10");
        yearsListAdapter.add("9");
        yearsListAdapter.add("8");
        yearsListAdapter.add("7");

        //텍스트뷰 등록
//        listYears = (TextView) findViewById(R.id.yearsText);
//        listYears.setText(listYearsNumber + "년");
        //이미지 버튼 등록
        ImageView leftMonthButton = (ImageView) yearsView.findViewById(R.id.leftyearsButton);
        leftMonthButton.setOnClickListener(this);
        ImageView rightMonthButton = (ImageView) yearsView.findViewById(R.id.rightyearsButton);
        rightMonthButton.setOnClickListener(this);

        return yearsView;
    }

    @Override
    public void onClick(View v) {/*
        switch (v.getId()) {
            case R.id.leftyearsButton:
                if(listYearsNumber != 1) {
                    listYearsNumber--;
                    listYears.setText(listYearsNumber + "년");
                }
                break;
            case R.id.rightyearsButton:
                listYearsNumber++;
                listYears.setText(listYearsNumber + "년");
                break;
        }*/
    }
    /*
    public int getListYearsNumber(){
        return listYearsNumber;
    }*/
}
