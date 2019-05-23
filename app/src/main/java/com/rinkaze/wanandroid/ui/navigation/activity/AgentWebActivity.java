package com.rinkaze.wanandroid.ui.navigation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.AgentWebView;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.officialpresenter.NaviWebPresenter;
import com.rinkaze.wanandroid.view.navigation.NaviWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgentWebActivity extends BaseActivity<NaviWebView, NaviWebPresenter> implements NaviWebView {
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.navi_toolbar)
    Toolbar naviToolbar;
    @BindView(R.id.navi_toolbar_title)
    TextView naviToolbarTitle;
    private AgentWeb mAgentWeb;

    @Override
    protected NaviWebPresenter initPresenter() {
        return new NaviWebPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent_web;
    }

    @Override
    protected void initView() {
        super.initView();
        String link = getIntent().getStringExtra("link");
//        String title = getIntent().getStringExtra("title");

        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(ll, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        naviToolbarTitle.setText(title);
                    }
                }) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(link);
        setSupportActionBar(naviToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"分享");
        menu.add(2,2,2,"收藏");
        menu.add(3,3,3,"用浏览器打开");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
