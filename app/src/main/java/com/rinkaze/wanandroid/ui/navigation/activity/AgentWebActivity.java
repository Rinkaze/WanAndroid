package com.rinkaze.wanandroid.ui.navigation.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.just.library.AgentWeb;
import com.just.library.AgentWebView;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.presenter.NaviLikePresenter;
import com.rinkaze.wanandroid.presenter.officialpresenter.NaviWebPresenter;
import com.rinkaze.wanandroid.ui.main.activity.LoginActivity;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.navigation.NaviLikeView;
import com.rinkaze.wanandroid.view.navigation.NaviWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgentWebActivity extends BaseActivity<NaviLikeView, NaviLikePresenter> implements NaviLikeView {
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.navi_toolbar)
    Toolbar naviToolbar;
    @BindView(R.id.navi_toolbar_title)
    TextView naviToolbarTitle;
    private AgentWeb mAgentWeb;
    private String link;


    @Override
    protected NaviLikePresenter initPresenter() {
        return new NaviLikePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent_web;
    }


    @Override
    protected void initView() {
        super.initView();
        link = getIntent().getStringExtra("link");
//        String title = getIntent().getStringExtra("title");
        naviToolbar.setTitle("");
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
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("*/*");
                intent1.putExtra(Intent.EXTRA_STREAM, link);
                startActivity(Intent.createChooser(intent1, "Share to..."));
                break;
            case 2:
                boolean param = (boolean) SpUtil.getParam(Constants.LOGIN, false);
                String name = (String) SpUtil.getParam(Constants.USERNAME, "");

                if (param){
                    mPresenter.initNaviLike(name,link);
                }else {
                    startActivityForResult(new Intent(AgentWebActivity.this,LoginActivity.class),100);
                    ToastUtil.showShort("请先登录");
                }
                break;
            case 3:
                initWeb(link);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initWeb(String link) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(link);
        intent.setData(content_url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
        startActivity(intent);
    }


    @Override
    public void onSuccess(String result) {
        ToastUtil.showShort(result);
    }

    @Override
    public void onFina(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == WanAndroidApi.SUCCESS_CODE){
            mPresenter.initNaviLike((String) SpUtil.getParam(Constants.USERNAME, ""),link);
        }
    }
}
