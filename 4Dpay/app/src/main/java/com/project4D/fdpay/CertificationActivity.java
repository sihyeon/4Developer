package com.project4D.fdpay;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.project4D.fdpay.manager.DBManager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * Check that the user phone number is correct or not when sign up
 * count of certification number is must be more then 5 letters
 **/
public class CertificationActivity extends Activity {
    private String securityNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        Button submit = (Button) findViewById(R.id.certification_submit);
        final EditText ed = (EditText) findViewById(R.id.certification_edittext);
        final SecurityKeyboardFragment secure = new SecurityKeyboardFragment();
        secure.initLimit(5);
        ed.setInputType(0);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setText("");
                secure.show(getFragmentManager(), "callByCerti");
            }
        });

        secure.setOnSecureKeyInputListener(new SecurityKeyboardFragment.SecureKeyInputListener() {
            @Override
            public void onInput(String input) {
                securityNum = input;
                for(int i = 0 ; i < input.length() ; ++i)
                    ed.append("●");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send number to server and check is it right later
                //hp....
                Bundle bundle = getIntent().getExtras();
                DBManager.setDbName(bundle.getString("USER_ID"));
                startActivity(new Intent(CertificationActivity.this, MainActivity.class));
            }
        });
    }
}
