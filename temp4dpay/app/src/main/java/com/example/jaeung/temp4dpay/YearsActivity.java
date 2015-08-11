package com.example.jaeung.temp4dpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Jaeung on 2015-07-28.
 */
public class YearsActivity extends Activity implements OnClickListener {
    private ListView mListView = null;
    private YearsListAdapter yearsListAdapter = null;
    private TextView listYears;
    public static Context yearsContext;

    private int listYearsNumber = ((CalendarActivity)CalendarActivity.calendarContext).getSetYear();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.years_view);
        yearsContext = this;
        //어댑터 생성
        yearsListAdapter = new YearsListAdapter();
        //리스트뷰 연결
        mListView = (ListView) findViewById(R.id.listView);
        //어댑터 연결
        mListView.setAdapter(yearsListAdapter);

        yearsListAdapter.add("12");
        yearsListAdapter.add("11");
        yearsListAdapter.add("10");
        yearsListAdapter.add("9");

        //텍스트뷰 등록
        listYears = (TextView) findViewById(R.id.yearsText);
        listYears.setText(listYearsNumber + "년");
        //이미지 버튼 등록
        ImageView leftMonthButton = (ImageView) findViewById(R.id.leftyearsButton);
        leftMonthButton.setOnClickListener(this);
        ImageView rightMonthButton = (ImageView) findViewById(R.id.rightyearsButton);
        rightMonthButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
        }
    }
    public void listClick(){
        Intent intent = new Intent(YearsActivity.this, CalendarActivity.class);
        startActivity(intent);
    }
    public int getListYearsNumber(){
        return listYearsNumber;
    }
}
