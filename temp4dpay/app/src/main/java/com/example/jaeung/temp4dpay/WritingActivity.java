package com.example.jaeung.temp4dpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jaeung on 2015-08-04.
 */
public class WritingActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing_view);



        //텍스트뷰 등록
        TextView MonthAndDay = (TextView) findViewById(R.id.writingDayText);
        MonthAndDay.setText(((CalendarActivity) CalendarActivity.calendarContext).getSetMonth() + "."
                + ((CalendarActivity) CalendarActivity.calendarContext).getSetDay());

        //버튼 생성 및 리스너 등록
        Button buttonIncome = (Button) findViewById(R.id.button_Income);
        buttonIncome.setOnClickListener(this);

        Button buttonSpend = (Button) findViewById(R.id.button_Spend);
        buttonSpend.setOnClickListener(this);

        Button buttonSaveAndContinue = (Button) findViewById(R.id.button_SaveAndContinue);
        buttonSaveAndContinue.setOnClickListener(this);

        Button buttonSaveAndEnd = (Button) findViewById(R.id.button_SaveAndEnd);
        buttonSaveAndEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Income:
                break;
            case R.id.button_Spend:

                break;
            case R.id.button_SaveAndContinue:
                break;
            case R.id.button_SaveAndEnd:
                MainActivity.tab_host.setCurrentTab(0);
                break;
        }
    }
}
