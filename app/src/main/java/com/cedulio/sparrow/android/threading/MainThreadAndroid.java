package com.cedulio.sparrow.android.threading;

import android.os.Handler;
import android.os.Looper;

import com.cedulio.sparrow.domain.executor.MainThread;


public class MainThreadAndroid implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadAndroid() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadAndroid();
        }

        return sMainThread;
    }

}