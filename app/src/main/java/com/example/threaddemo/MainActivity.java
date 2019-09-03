package com.example.threaddemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextFirst, mTextSecond;
    private static final int START = 100;
    private static final int SEND1 = 101;
    Handler mHandler = new Handler();
    Thread mThread;
    Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // Thread
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Message message = new Message();
                    message.what = SEND1;
                    message.arg1 = i;
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Runnable
      /*  mRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<10;i++){
                    Message message = new Message();
                    message.what = SEND1;
                    message.arg1=i;
                    mHandler.sendMessage(message);
                    Log.d("TAG","==>" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };*/

        mTextFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.sendEmptyMessage(START);
            }
        });
    }

    private void initView() {
        mTextFirst = findViewById(R.id.text_numberOne);
        mTextSecond = findViewById(R.id.txt_second);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onResume() {
        super.onResume();
        mHandler = new Handler() {
            @SuppressLint("SetTextI18n")
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == START) {
                    mThread.start();
                } else if (msg.what == SEND1) {
                    mTextSecond.setText("Count: " + msg.arg1);
                }
            }
        };
    }
}
