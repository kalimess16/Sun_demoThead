package com.example.threaddemo;

import android.os.Handler;
import android.os.Message;

public class Runnable_Demo implements Runnable {

    private int seconds;
    //private String mString;
    private static final String LOG_SECONDS = "log";
    private static final int SEND1 = 101;
    private Handler mHandler;

    public Runnable_Demo(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < seconds; i++) {
            Message message = new Message();
            message.what = SEND1;
            message.arg1 = i;
            mHandler.sendMessage(message);
        }
    }
}
