package com.rinkaze.wanandroid.ui.navigation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.ui.navigation.fragment.NaviFragment;

public class EmptyActivity extends AppCompatActivity {

    /**
     * 点击
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl,new NaviFragment());
        transaction.commit();
    }

    private void initView() {

    }
}
