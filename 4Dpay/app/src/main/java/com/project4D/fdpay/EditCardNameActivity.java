package com.project4D.fdpay;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.project4D.fdpay.event.EventManager;
import com.project4D.fdpay.event.UListener;
import com.project4D.fdpay.manager.CreditCardTableManager;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.ViewUtil;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 **/
public class EditCardNameActivity extends AppCompatActivity {
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private CreditCardTableManager cm = new CreditCardTableManager(this);
    private EventManager eventManager = EventManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card_name);



        final Bundle bundle = getIntent().getExtras();
        final String cardName = cm.getCardNameById(bundle.getInt("ID"));
        vu.editText(R.id.editcardname_edittext).setText(cardName);
        vu.button(R.id.editcardname_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCardName = vu.text(vu.editText(R.id.editcardname_edittext));
                if(cardName.equals(newCardName)){
                    new AlertDialogHelper(EditCardNameActivity.this)
                            .setTitle("카드 이름이 같습니다.")
                            .setMessage("확인해 주세요.")
                            .setPositiveButton("확인", null)
                            .build();
                } else {
                    cm.updateCardNameById(newCardName, bundle.getInt("ID"));
                    Bundle b = new Bundle();
                    b.putInt("ID", bundle.getInt("ID"));
                    b.putString("NAME", newCardName);
                    for(UListener ul : eventManager.getListener("EDIT_CREDIT")){
                        ul.onSuccess(b);
                    }
                    finish();
                }
            }
        });

        //EditText 포커스 시 커서 위치 맨 뒤로 이동
        //9 .19 Sujeong
        vu.editText(R.id.editcardname_edittext).setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    vu.editText(R.id.editcardname_edittext).setSelection(vu.editText(R.id.editcardname_edittext).getText().length());
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
