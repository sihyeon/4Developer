package com.project4D.fdpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.project4D.fdpay.manager.CreditCardTableManager;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.ViewUtil;

public class EditCardNameActivity extends AppCompatActivity {
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private CreditCardTableManager cm = new CreditCardTableManager(this);
    public static Bundle cardNameChanged = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card_name);

        final Bundle b = getIntent().getExtras();
        final String cardName = cm.getCardNameById(b.getInt("ID"));
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
                    cm.updateCardNameById(newCardName, b.getInt("ID"));
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", b.getInt("ID"));
                    bundle.putString("NAME", newCardName);
                    cardNameChanged = bundle;
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cardNameChanged = null;
    }
}
