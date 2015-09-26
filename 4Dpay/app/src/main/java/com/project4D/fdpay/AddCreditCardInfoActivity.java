package com.project4D.fdpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project4D.fdpay.event.EventManager;
import com.project4D.fdpay.event.UListener;
import com.project4D.fdpay.manager.CreditCardTableManager;
import com.project4D.fdpay.model.CreditCardInfo;
import com.project4D.fdpay.util.ViewUtil;

public class AddCreditCardInfoActivity extends Activity {
    private ViewUtil.Finder vu = ViewUtil.finder(this);
    private EventManager eventManager = EventManager.getInstance();
    private CreditCardTableManager db;
    private String cardnumber;
    private String cardname;
    private String valid;
    private String password;
    private String cvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card_info);
        db = new CreditCardTableManager(this);
        final SecurityKeyboardFragment secureByPw = new SecurityKeyboardFragment();
        secureByPw.initLimit(4);
        final SecurityKeyboardFragment secureByCvc = new SecurityKeyboardFragment();
        secureByCvc.initLimit(3);

        //EditText 포커스 순서
        //9. 19. Sujeong
        vu.editText(R.id.addcreditcard_cardnumber4).setNextFocusDownId(R.id.addcreditcard_vaildmonth);
        vu.editText(R.id.addcreditcard_vaildmonth).setNextFocusDownId(R.id.addcreditcard_vaildyear);
        vu.editText(R.id.addcreditcard_vaildyear).setNextFocusDownId(R.id.addcreditcard_password);
        vu.editText(R.id.addcreditcard_password).setNextFocusDownId(R.id.addcreditcard_cardname);
        vu.editText(R.id.addcreditcard_cardname).setNextFocusDownId(R.id.addcreditcard_cvcnumber);

        final EditText pwEdit = vu.editText(R.id.addcreditcard_password);
        pwEdit.setInputType(0);
        pwEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    pwEdit.setText("");
                    secureByPw.show(getFragmentManager(), "callByPw");
                }

            }
        });
        secureByPw.setOnSecureKeyInputListener(new SecurityKeyboardFragment.SecureKeyInputListener() {
            @Override
            public void onInput(String input) {
                password = input;
                for (int i = 0; i < input.length(); ++i) {
                    pwEdit.append("●");
                }
            }
        });

        final EditText cvcEdit = vu.editText(R.id.addcreditcard_cvcnumber);
        cvcEdit.setInputType(0);
        cvcEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                cvcEdit.setText("");
                secureByCvc.show(getFragmentManager(), "callByCvc");
            }
        });
        secureByCvc.setOnSecureKeyInputListener(new SecurityKeyboardFragment.SecureKeyInputListener() {
            @Override
            public void onInput(String input) {
                cvc = input;
                for(int i = 0 ; i < input.length() ; ++i)
                    cvcEdit.append("●");
            }
        });

        vu.button(R.id.addcreditcard_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardnumber = vu.text(vu.editText(R.id.addcreditcard_cardnumber1)) +
                        vu.text(vu.editText(R.id.addcreditcard_cardnumber2)) +
                        vu.text(vu.editText(R.id.addcreditcard_cardnumber3)) +
                        vu.text(vu.editText(R.id.addcreditcard_cardnumber4));
                cardname = vu.text(vu.editText(R.id.addcreditcard_cardname));
                valid = vu.text(vu.editText(R.id.addcreditcard_vaildyear)) +
                        vu.text(vu.editText(R.id.addcreditcard_vaildmonth));
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
                CreditCardInfo card = new CreditCardInfo(cardnumber, Integer.parseInt(valid),
                        Integer.parseInt(password), cardname, Integer.parseInt(cvc)
                );
                //TODO send data to server
                //HttpPoster.executePOST();

                Bundle b = new Bundle();
                b.putInt("ID", db.add(card));
                b.putString("NAME", card.getCardName());
                for (UListener ul : eventManager.getListener("ADD_CREDIT")) {
                    ul.onSuccess(b);
                }
                finish();
            }
        });


    }


    private void setFocus(EditText ed) {
        ed.setFocusable(true);
        ed.invalidate();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
