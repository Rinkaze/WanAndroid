package com.rinkaze.wanandroid.ui.project.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.view.EmptyView;

import butterknife.BindView;

public class ProSubActivity extends BaseActivity<EmptyView,EmptyPresenter> implements EmptyView,View.OnClickListener {

    @BindView(R.id.pro_container)
    LinearLayout container;
    private String link;
    private String name;
    private AgentWeb mAgentWeb;
    @BindView(R.id.pro_toolbar)
    Toolbar mTob;
    @BindView(R.id.pro_img)
    ImageView mImg;
    @BindView(R.id.pro_title)
    TextView mTitle;
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
        link = getIntent().getStringExtra("link");
        name = getIntent().getStringExtra("name");
        mTitle.setText(name);
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
                intent1.setType("*/*");
                intent1.putExtra(Intent.EXTRA_STREAM, link);
                startActivity(Intent.createChooser(intent1, "Share to..."));
                break;
            case 2:
                break;
            case 3:
                Uri parse = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW,parse);
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
}