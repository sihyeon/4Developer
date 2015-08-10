package com.project4D.fdpay.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.Gson;
import com.project4D.fdpay.internal.Constant;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 */
public class HttpPoster {
	/**
	 * Thread-safe asynchronous method <br />
	 * Send Object (transformed as JSON object) to Server by HTTP POST method in newly created thread
	 * @param obj object which to be submitted
	 * @param httpPosterCallBack 
	 */
	public static void executePOST(final Object obj, final HttpPosterCallBack httpPosterCallBack) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String requestContent = new Gson().toJson(obj);
					HttpURLConnection con = (HttpURLConnection) new URL(Constant.serverURL).openConnection();
					setupHeader(con);
					writeRequestContent(requestContent, con);
					httpPosterCallBack.onSuccess(readResponseContent(con));
				} catch (Exception e) {
					httpPosterCallBack.onError(e);
				}
			}
		}).start();
	}

	private static void setupHeader(HttpURLConnection con) throws ProtocolException {
		con.setConnectTimeout(10000); // 10sec
		con.setReadTimeout(10000);
		con.setRequestMethod("POST");
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		con.setDoInput(true);
	}

	private static void writeRequestContent(String requestContent, HttpURLConnection con) throws Exception {
		BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
		out.write(requestContent.getBytes("utf-8"));
		out.flush();
		out.close();
	}

	private static String readResponseContent(HttpURLConnection con) throws Exception {
		if (con.getResponseCode() != HttpURLConnection.HTTP_OK)
			throw new HttpRetryException("Response is not HTTP_OK", con.getResponseCode());

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		String gotten = null;
		StringBuilder builder = new StringBuilder();
		while ((gotten = in.readLine()) != null) {
			builder.append(gotten);
		}
		in.close();
		
		return builder.toString();
	}
}
