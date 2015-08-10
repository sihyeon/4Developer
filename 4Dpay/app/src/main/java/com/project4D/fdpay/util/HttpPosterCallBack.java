package com.project4D.fdpay.util;

public interface HttpPosterCallBack {
	void onSuccess(String response);
	void onError(Throwable error);
}
