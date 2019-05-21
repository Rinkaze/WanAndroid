package com.rinkaze.wanandroid.ui.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.view.EmptyView;

import butterknife.BindView;

public class ProSubActivity extends BaseActivity<EmptyView,EmptyPresenter> implements EmptyView{

    @BindView(R.id.pro_web)
    WebView mWv;
    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pro_sub;
    }
    @Override
    protected void initView() {
        //接收
        Intent intent = getIntent();
        String name =  intent.getStringExtra("link");
        mWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String s) {
                mWv.loadUrl(s);
                return super.shouldOverrideUrlLoading(view, s);
            }
        });
        //加载
        mWv.loadUrl(name);
        //支持js
        WebSettings settings = mWv.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}