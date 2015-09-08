package com.project4D.fdpay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.project4D.fdpay.manager.CreditCardDBManager;
import com.project4D.fdpay.manager.PointCardDBManager;
import com.project4D.fdpay.util.ViewUtil;


public class ShowCardInfoActivity extends ActionBarActivity {
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private CreditCardDBManager cm = new CreditCardDBManager(this);
    private PointCardDBManager pm = new PointCardDBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_info);

        Bundle b = getIntent().getExtras();
        if(b.getString("CLASS_NAME").equals("CreditCardFragment"))
            vu.textView(R.id.showcardinfo_cardname).setText(cm.getCardNameById(b.getInt("POSITION")));
        else
            vu.textView(R.id.showcardinfo_cardname).setText(pm.getCardNameById(b.getInt("POSITION")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_info, menu);
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
