package com.project4D.fdpay.event;

/**
 * Created by Administrator on 2015-09-13.
 * Universal  Listener
 */
public interface UListener<Success, Process> {
    public void onSuccess(Success s);
    public void onFail(Throwable t);
    public void onProcess(Process p);
}
