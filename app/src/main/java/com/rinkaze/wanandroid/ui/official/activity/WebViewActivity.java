package com.rinkaze.wanandroid.ui.official.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;


import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.officialpresenter.WebPresenter;
import com.rinkaze.wanandroid.view.officialview.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity<WebView, WebPresenter> implements WebView {


    @BindView(R.id.container)
    RelativeLayout mContainer;
    private AgentWeb agentWeb;

    @Override
    protected WebPresenter initPresenter() {
        return new WebPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
        super.initData();
        String url = getIntent().getStringExtra("url");
        final String name = getIntent().getStringExtra("name");
        ChromeClientCallbackManager.ReceivedTitleCallback mCallback;
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContainer, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .createAgentWeb()
                .ready()
                .go(url);

    }


}
