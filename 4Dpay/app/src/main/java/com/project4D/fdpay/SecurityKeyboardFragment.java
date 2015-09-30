package com.project4D.fdpay;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.project4D.fdpay.util.ViewUtil;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 **/
public class SecurityKeyboardFragment extends DialogFragment {
    private List<Integer> numList = new ArrayList<>();
    private SecureRandom secureRandom = new SecureRandom();
    private List<Integer> input = new ArrayList<>();
    private EditText editText;
    private int max;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initKeyBoard();
    }

    public void initLimit(int max) {
        this.max = max;
    }

    public void initKeyBoard() {
        for (int i = 0 ; i < 10 ; ++i) numList.add(i);
        Collections.shuffle(numList, secureRandom);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View dialog = inflater.inflate(R.layout.fragment_security_keyboard, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ViewUtil.Finder vu = ViewUtil.finder(dialog);
        editText = (EditText) dialog.findViewById(R.id.sec_edit);
        editText.setEnabled(false);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        List<Button> buttons = new ArrayList<>();
        buttons.add(vu.button(R.id.seckey_k0));
        buttons.add(vu.button(R.id.seckey_k1));
        buttons.add(vu.button(R.id.seckey_k2));
        buttons.add(vu.button(R.id.seckey_k3));
        buttons.add(vu.button(R.id.seckey_k4));
        buttons.add(vu.button(R.id.seckey_k5));
        buttons.add(vu.button(R.id.seckey_k6));
        buttons.add(vu.button(R.id.seckey_k7));
        buttons.add(vu.button(R.id.seckey_k8));
        buttons.add(vu.button(R.id.seckey_k9));

        for (int i = 0 ; i < buttons.size() ; ++i) {
            buttons.get(i).setText(String.valueOf(numList.get(i)));
            buttons.get(i).setOnClickListener(new KeyboardClickListener(numList.get(i)));
        }

        vu.button(R.id.seckey_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (client == null)
                    throw new IllegalStateException("You must implement client");

                if(input.size() < max) return;

                StringBuffer s = new StringBuffer();
                for (int e : input) s.append(e);
                client.onInput(s.toString());
                dismiss();
            }
        });

        vu.button(R.id.seckey_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.size() >= 1) {
                    input.remove(input.size() - 1);
                    CharSequence text = editText.getText();
                    editText.setText(text.subSequence(0, text.length()-1));
                }
            }
        });

        return  dialog;
    }

    private class KeyboardClickListener implements View.OnClickListener {
        int value;

        private KeyboardClickListener(int value){
            this.value = value;
        }
        @Override
        public void onClick(View v) {
            editText.append("●");
            input.add(value);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        editText.setText("");
        input.clear();
    }

    /////////////////////////////// For clients /////////////////////////////////
    private SecureKeyInputListener client;

    public interface SecureKeyInputListener {
        void onInput(String input);
    }

    public void setOnSecureKeyInputListener(SecureKeyInputListener listener) {
        this.client = listener;
    }
    /////////////////////////////////////////////////////////////////////////////
}