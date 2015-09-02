package com.project4D.fdpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project4D.fdpay.model.CardInfo;
import com.project4D.fdpay.util.CreditCardDBManager;
import com.project4D.fdpay.util.ViewUtil;

public class AddCreditCardInfoActivity extends Activity {
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private CreditCardDBManager db;
    private String cardnumber;
    private String cardname;
    private String valid;
    private String password;
    private String cvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card_info);
        db = CreditCardDBManager.newCreditCardDBManager(this);

        vu.button(R.id.addcreditcard_submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardnumber = vu.text(vu.editText(R.id.addcreditcard_cardnumber1)) +
                                vu.text(vu.editText(R.id.addcreditcard_cardnumber2)) +
                                vu.text(vu.editText(R.id.addcreditcard_cardnumber3)) +
                                vu.text(vu.editText(R.id.addcreditcard_cardnumber4));
                        cardname = vu.text(vu.editText(R.id.addcreditcard_cardname));
                        password = vu.text(vu.editText(R.id.addcreditcard_password));
                        valid = vu.text(vu.editText(R.id.addcreditcard_vaildyear)) +
                                vu.text(vu.editText(R.id.addcreditcard_vaildmonth));
                        cvc = vu.text(vu.editText(R.id.addcreditcard_cvcnumber));
                        //TODO to show that this activity can attach card.
                        if (cardname.isEmpty()) {
                            Toast.makeText(AddCreditCardInfoActivity.this, "카드 이름을 입력해 주세요", Toast.LENGTH_LONG).show();
                            setFocus(vu.editText(R.id.addcreditcard_cardname));
                            return;
                        } else if (cardnumber.isEmpty()) {
                            Toast.makeText(AddCreditCardInfoActivity.this, "카드 번호를 입력해 주세요", Toast.LENGTH_LONG).show();
                            setFocus(vu.editText(R.id.addcreditcard_cardnumber1));
                            return;
                        } else if (password.isEmpty()) {
                            Toast.makeText(AddCreditCardInfoActivity.this, "비밀번호를 입력해 주세요", Toast.LENGTH_LONG).show();
                            setFocus(vu.editText(R.id.addcreditcard_password));
                            return;
                        } else if (valid.isEmpty()) {
                            Toast.makeText(AddCreditCardInfoActivity.this, "유효기간을 입력해 주세요", Toast.LENGTH_LONG).show();
                            setFocus(vu.editText(R.id.addcreditcard_vaildyear));
                            return;
                        } else if (cvc.isEmpty()) {
                            Toast.makeText(AddCreditCardInfoActivity.this, "cvc를 입력해 주세요", Toast.LENGTH_LONG).show();
                            setFocus(vu.editText(R.id.addcreditcard_cvcnumber));
                            return;
                        }
                        //(int cardNum, int cardValid, int password, String cardName, int cvc)
                        CardInfo card = new CardInfo(cardnumber, Integer.parseInt(valid),
                                Integer.parseInt(password), cardname, Integer.parseInt(cvc)
                        );
                        //TODO send data to server
                        //HttpPoster.executePOST();
                        db.add(card);
                        finish();
                    }
                });
    }

    private void setFocus(EditText ed) {
        ed.setFocusable(true);
        ed.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
