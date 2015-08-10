package com.project4D.fdpay;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.project4D.fdpay.util.AlertDialogHelper;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set this activity as full-screen activity
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_splash);
		new Starter().start();
	}
	
	private class Starter extends Thread {
		@Override
		public void run() {
			// Delay action by 1sec
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// Run splash image animation (slide-in effect)
					final ImageView iv =  (ImageView) findViewById(R.id.imageView1);
					iv.setVisibility(View.VISIBLE);
					iv.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, android.R.anim.slide_in_left));
					// Show alert dialog when user havn't been logged
					if(!com.project4D.fdpay.internal.State.isLogin()){
						try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
						new AlertDialogHelper(SplashActivity.this)
							.setTitle("로그인 해주세요")
							.setMessage("처음이신가요? 로그인 해주세요")
							.setPositiveButton("확인", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									startActivity(new Intent(SplashActivity.this, SignInActivity.class));
								}
							}).build();
					}
				}
			});
			
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	    case KeyEvent.KEYCODE_BACK:
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}
}
