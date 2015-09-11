package com.project4D.fdpay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.project4D.fdpay.manager.CreditCardTableManager;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.ViewUtil;


public class ShowCardInfoActivity extends ActionBarActivity {
    public static Bundle deleteCardInfo = null;
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private CreditCardTableManager cm = new CreditCardTableManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_info);

        final Bundle b = getIntent().getExtras();
        vu.textView(R.id.showcardinfo_cardname).setText(cm.getCardNameById(b.getInt("ID")));
        vu.button(R.id.showcardinfo_editcardbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowCardInfoActivity.this, EditCardNameActivity.class);
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        });
        vu.button(R.id.showcardinfo_deletecardbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialogHelper(ShowCardInfoActivity.this)
                        .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cm.deleteCardInfoById(b.getInt("ID"));

                                Bundle bundle = new Bundle();
                                bundle.putInt("ID", b.getInt("ID"));
                                deleteCardInfo = bundle;
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
        deleteCardInfo = null;
    }
}
