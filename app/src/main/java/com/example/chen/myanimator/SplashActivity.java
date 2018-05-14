package com.example.chen.myanimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class SplashActivity extends AppCompatActivity {
    private ImageView iv_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iv_splash = findViewById(R.id.iv_splash);
        iv_splash.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}
