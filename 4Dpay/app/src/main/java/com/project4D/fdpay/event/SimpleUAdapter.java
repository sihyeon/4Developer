package com.project4D.fdpay.event;

import android.os.Bundle;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * @version 2015-09-14.
 */
public abstract class SimpleUAdapter<Success> implements UListener<Success, Void> {
    @Override
    public abstract void onSuccess(Success s);

    @Override
    public final void onFail(Throwable t) {}

    @Override
    public final void onProcess(Void p) {}
}
