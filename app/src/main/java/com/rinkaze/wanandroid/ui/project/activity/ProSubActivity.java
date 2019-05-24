package com.rinkaze.wanandroid.ui.project.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.presenter.NaviLikePresenter;
import com.rinkaze.wanandroid.ui.main.activity.LoginActivity;
import com.rinkaze.wanandroid.ui.navigation.activity.AgentWebActivity;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.EmptyView;
import com.rinkaze.wanandroid.view.NaviView;
import com.rinkaze.wanandroid.view.navigation.NaviLikeView;

import butterknife.BindView;

public class ProSubActivity extends BaseActivity<NaviLikeView, NaviLikePresenter> implements NaviLikeView, View.OnClickListener {

    @BindView(R.id.pro_container)
    LinearLayout container;
    private String link;
    private String title;
    private String author;
    private AgentWeb mAgentWeb;
    @BindView(R.id.pro_toolbar)
    Toolbar mTob;
    @BindView(R.id.pro_img)
    ImageView mImg;
    @BindView(R.id.pro_title)
    TextView mTitle;


    @Override
    protected void initView() {
        link = getIntent().getStringExtra("link");
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        mTitle.setText(title);
        mTob.setTitle(" ");
        setSupportActionBar(mTob);
        ChromeClientCallbackManager.ReceivedTitleCallback mCallback;
        mCallback = null;
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .createAgentWeb()
                .ready()
                .go(link);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pro_sub;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "分享");
        menu.add(1, 2, 2, "收藏");
        menu.add(1, 3, 3, "用浏览器打开");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(intent1, link));
                break;
            case 2:
                boolean param = (boolean) SpUtil.getParam(Constants.LOGIN, false);
                String name = (String) SpUtil.getParam(Constants.USERNAME, "");
                if (param==true) {
                    mPresenter.initNaviLike(title,author,link);
                } else {
                    startActivityForResult(new Intent(ProSubActivity.this, LoginActivity.class), 100);
                    ToastUtil.showShort("请先登录");
                }
                break;
            case 3:
                Uri parse = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, parse);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected NaviLikePresenter initPresenter() {
        return new NaviLikePresenter();
    }

    @Override
    protected void initListener() {
        mImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pro_img:
                if (!mAgentWeb.back()) {
                    finish();
                }
                break;
        }
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
        if (requestCode == 100 && resultCode == WanAndroidApi.SUCCESS_CODE) {
            mPresenter.initNaviLike(title,author, link);
        }
    }
}