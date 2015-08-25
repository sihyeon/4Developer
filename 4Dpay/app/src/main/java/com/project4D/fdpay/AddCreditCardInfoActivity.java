package com.project4D.fdpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project4D.fdpay.model.CardModel;
import com.project4D.fdpay.util.HttpPoster;
import com.project4D.fdpay.util.ViewUtil;

public class AddCreditCardInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card_info);
        final TextView cardnumber1 = (TextView) findViewById(R.id.addcreditcard_cardnumber1);
        final TextView cardnumber2 = (TextView) findViewById(R.id.addcreditcard_cardnumber2);
        final TextView cardnumber3 = (TextView) findViewById(R.id.addcreditcard_cardnumber3);
        final TextView cardnumber4 = (TextView) findViewById(R.id.addcreditcard_cardnumber4);
        final TextView cardname = (TextView) findViewById(R.id.addcreditcard_cardname);
        TextView password = (TextView) findViewById(R.id.addcreditcard_password);
        TextView validmonth = (TextView) findViewById(R.id.addcreditcard_vaildmonth);
        TextView validyear = (TextView) findViewById(R.id.addcreditcard_vaildyear);
        TextView cvcnumber = (TextView) findViewById(R.id.addcreditcard_cvcnumber);
        Button submit = (Button) findViewById(R.id.addcreditcard_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardnumber = cardnumber1.toString()+cardnumber2.toString()+cardnumber3.toString()+cardnumber4.toString();
                //int password =
                //TODO send data
                //HttpPoster.executePOST();
                //TODO to show that this activity can attach card.
                if(cardname.getText().toString().isEmpty()) {
                    Toast.makeText(AddCreditCardInfoActivity.this, "이름을 입력해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                setResult(1, new Intent().putExtra("cardName", cardname.getText().toString()));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_card_info, menu);
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
