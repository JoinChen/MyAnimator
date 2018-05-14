package com.example.chen.myanimator;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.chen.myanimator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class MyHandlerActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button btn_1s;
    @BindView(R.id.button2)
    Button btn_3s;
    @BindView(R.id.button3)
    Button btn_cancle;

    private HandlerThread mHandlerThread;
    private Handler mainHandler,twoHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);
        mHandlerThread = new HandlerThread("handlerThread");
        mHandlerThread.start();
        
    }
}
