package com.example.jaeung.temp4dpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class MainActivity extends Activity {
    public static TabHost tab_host;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        /*
        setContentView(R.layout.house_hold_tab);

        tab_host = (TabHost) findViewById(R.id.HouseHoldTab);
        tab_host.setup(this.getLocalActivityManager());

        TabSpec calendarViewTab = tab_host.newTabSpec("TAB2");
        calendarViewTab.setIndicator("달력");
        calendarViewTab.setContent(new Intent(MainActivity.this, CalendarActivity.class));
        tab_host.addTab(calendarViewTab);

        TabSpec yearsViewTab = tab_host.newTabSpec("TAB3");
        yearsViewTab.setIndicator("일년 보기");
        yearsViewTab.setContent(new Intent(MainActivity.this, YearsActivity.class));
        tab_host.addTab(yearsViewTab);

        TabSpec writingViewTab = tab_host.newTabSpec("TAB1");
        writingViewTab.setIndicator("쓰기");
        writingViewTab.setContent(new Intent(MainActivity.this, WritingActivity.class));
        tab_host.addTab(writingViewTab);

        TabSpec CategorizeViewTab = tab_host.newTabSpec("TAB4");
        CategorizeViewTab.setIndicator("분류");
        CategorizeViewTab.setContent(new Intent(MainActivity.this, CategorizeActivity.class));
        tab_host.addTab(CategorizeViewTab);

        TabSpec ts5 = tab_host.newTabSpec("TAB5");
        ts5.setIndicator("환경 설정");
        ts5.setContent(R.id.TabHouseHoldSeting);
        tab_host.addTab(ts5);

        //실행시 탭을 달력으로 전환
        tab_host.setCurrentTab(0);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
