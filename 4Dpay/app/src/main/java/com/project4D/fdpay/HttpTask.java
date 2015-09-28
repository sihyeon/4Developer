package com.project4D.fdpay;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jaeung on 2015-09-27.
 */
public class HttpTask{
    URL httpURL;
    public HttpURLConnection httpURLConnection;
    public HttpTask(){
        try {
            httpURL = new URL("URL을 입력");
            httpURLConnection = (HttpURLConnection) httpURL.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void httpConnect(){

    }
}
