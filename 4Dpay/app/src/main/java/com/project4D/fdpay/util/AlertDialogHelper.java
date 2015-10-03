package com.project4D.fdpay.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;

import com.project4D.fdpay.R;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * renual alert dialog
 */
public class AlertDialogHelper {
	private final AlertDialog.Builder builder;
	private final Activity activity;

	public AlertDialogHelper(Activity activity) {
		this.activity = activity;
		this.builder = new AlertDialog.Builder(activity);
	}
	
	public AlertDialogHelper setTitle(CharSequence title) {
		builder.setTitle(title);
		return this;
	}

	public AlertDialogHelper setMessage(CharSequence Message) {
		builder.setMessage(Message);
		return this;
	}
	
	public AlertDialogHelper setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
		builder.setPositiveButton(text, listener);
		return this;
	}

	public AlertDialogHelper setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
		builder.setNegativeButton(text, listener);
		return this;
	}

	public void build() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				builder.create().show();
			}
		});
	}
	
	public static void showErrorDialog(Activity activity, Throwable error) {
		new AlertDialogHelper(activity)
			.setTitle("오류")
			.setMessage(error.getMessage())
			.setPositiveButton("확인", null)
			.build();
	}
}
