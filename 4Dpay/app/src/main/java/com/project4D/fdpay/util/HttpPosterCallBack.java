package com.project4D.fdpay.util;

/**
 * interface ( frame )
 */
public interface HttpPosterCallBack {
	void onSuccess(String response);
	void onError(Throwable error);
}
