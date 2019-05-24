package com.rinkaze.wanandroid.ui.white.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.ui.main.activity.MainActivity;

/*
* 黑白屏问题解决
* */
public class WhiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white);
        Intent intent = new Intent(WhiteActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
