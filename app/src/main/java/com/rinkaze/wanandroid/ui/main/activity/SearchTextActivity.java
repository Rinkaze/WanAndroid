package com.rinkaze.wanandroid.ui.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.view.EmptyView;
public class SearchTextActivity extends BaseActivity<EmptyView, EmptyPresenter>implements EmptyView {

    private String linkurl;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_text;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        linkurl = intent.getStringExtra("linkurl");
    }

    @Override
    protected void initData() {
        ChromeClientCallbackManager.ReceivedTitleCallback mCallback;
        mCallback = null;

    }
}
