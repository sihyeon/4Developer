package com.project4D.fdpay;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.project4D.fdpay.event.EventManager;
import com.project4D.fdpay.event.UListener;
import com.project4D.fdpay.manager.PointCardTableManager;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.ViewUtil;

public class ShowPointCardInfoActivity extends AppCompatActivity {
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private PointCardTableManager pm = new PointCardTableManager(this);
    private EventManager eventManager = EventManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point_card_info);

        final Bundle bundle = getIntent().getExtras();
        vu.textView(R.id.showpointcard_cardname).setText(pm.getCardNameById(bundle.getInt("ID")));
        vu.button(R.id.showpointcard_deletecardbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialogHelper(ShowPointCardInfoActivity.this)
                        .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pm.deleteCardInfoById(bundle.getInt("ID"));
                                Bundle b = new Bundle();
                                b.putInt("ID", bundle.getInt("ID"));
                                for(UListener ul : eventManager.getListener("DELETE_POINT")){
                                    ul.onSuccess(b);
                                }
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .build();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}