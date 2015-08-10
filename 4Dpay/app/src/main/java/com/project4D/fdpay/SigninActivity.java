package com.project4D.fdpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.project4D.fdpay.model.User;
import com.project4D.fdpay.model.UserBuilder;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.util.HttpPoster;
import com.project4D.fdpay.util.HttpPosterCallBack;
import com.project4D.fdpay.util.ViewUtil;
import com.project4D.fdpay.util.ViewUtil.Finder;

import org.json.JSONException;

public class SignInActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		final Finder vu = ViewUtil.finder(this);
		
		vu.button(R.id.signin_signinbutton).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String id = vu.text(vu.editText(R.id.signin_id));
				char[] pw = vu.text(vu.editText(R.id.signin_password)).toCharArray();
				try {
					signIn(id, pw);
				} catch (JSONException e) {e.printStackTrace();}
			}
		});
		
		vu.textView(R.id.signin_signuptext).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	    case KeyEvent.KEYCODE_BACK:
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}


	private void signIn(String id, char[] pw) throws JSONException {
		if (id.length() < 4 || pw.length < 4) {
			return;
		}
		
		User u = new UserBuilder().setID(id).setPW(pw).build();
		HttpPoster.executePOST(u, new HttpPosterCallBack() {
			@Override
			public void onSuccess(String response) {
				//TODO CardActivity
				startActivity(new Intent(SignInActivity.this, CardActivity.class));
			}
			@Override
			public void onError(Throwable error) {
				AlertDialogHelper.showErrorDialog(SignInActivity.this, error);
			}
		});
	}
}
