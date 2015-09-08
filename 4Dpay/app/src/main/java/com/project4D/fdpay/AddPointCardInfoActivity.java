package com.project4D.fdpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.project4D.fdpay.manager.PointCardDBManager;
import com.project4D.fdpay.model.PointCardInfo;

import java.util.ArrayList;
import java.util.List;

public class AddPointCardInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static PointCardInfo newlyCardInfo = null;

    private PointCardDBManager db;
    private String selectedCardName = null;
    List<String> cardName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point_card_info);

        db = new PointCardDBManager(this);
        cardName.add("CJ ONE");
        cardName.add("MEGABOX");
        cardName.add("CONVERSE");
        cardName.add("REEBOK");
        cardName.add("HAPPY POINT");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cardName);
        Spinner sp = (Spinner) findViewById(R.id.addpointcard_cardtype);
        sp.setPrompt("카드 종류를 선택하세요");
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        findViewById(R.id.addpointcard_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointCardInfo pi = new PointCardInfo(selectedCardName);
                db.add(pi);
                newlyCardInfo = pi;
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        newlyCardInfo = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCardName = cardName.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //EMTPY
    }
}
