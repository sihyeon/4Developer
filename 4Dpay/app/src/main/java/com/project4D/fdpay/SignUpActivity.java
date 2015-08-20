package com.project4D.fdpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.project4D.fdpay.model.User;
import com.project4D.fdpay.model.UserBuilder;
import com.project4D.fdpay.util.AlertDialogHelper;
import com.project4D.fdpay.internal.Constant;
import com.project4D.fdpay.util.HttpPoster;
import com.project4D.fdpay.util.HttpPosterCallBack;
import com.project4D.fdpay.util.ViewUtil;
import com.project4D.fdpay.util.ViewUtil.Finder;

public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		final Finder vu = ViewUtil.finder(this);
		
		vu.button(R.id.signup_signupbutton).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String id = vu.text(vu.editText(R.id.signup_id));
				char[] pw = vu.text(vu.editText(R.id.signup_password)).toCharArray();
				String email = vu.text(vu.editText(R.id.signup_email));
				String phone = vu.text(vu.editText(R.id.signup_phonenumber));
				signUp(id, pw, email, phone);
			}
		});
	}
	
	private void signUp(String id, char[] pw, String email, String phone) {
		User u = new UserBuilder().setID(id).setPW(pw).setEmail(email).setPhone(phone).build();
		//TODO request Later;
		//requestSignup(u);
		//////////////////////////////////////////////////////////////////////////////////////////////TODO MainActivity
		startActivity(new Intent(SignUpActivity.this, CertificationActivity.class));
	}
	
	private void requestSignup(User u){
		HttpPoster.executePOST(u, new HttpPosterCallBack() {
			@Override
			public void onSuccess(String response) {
				if (response.equals(Constant.signUpOK)) {
					startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
				} else if (response.equals(Constant.idDuplicated)) {
					new AlertDialogHelper(SignUpActivity.this)
							.setTitle("아이디 중복")
							.setMessage("다시 시도해 주세요")
							.setPositiveButton("확인", null)
							.build();
				}
			}
			@Override
			public void onError(final Throwable error) {
				AlertDialogHelper.showErrorDialog(SignUpActivity.this, error);
			}
		});
	}
	
}
