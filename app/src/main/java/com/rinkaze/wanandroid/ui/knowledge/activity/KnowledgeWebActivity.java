package com.rinkaze.wanandroid.ui.knowledge.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeWebActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.webtool_img)
    ImageView mWebtoolImg;
    @BindView(R.id.webtool_tv)
    TextView mWebtoolTv;
    @BindView(R.id.webtoolbar)
    Toolbar mWebtoolbar;
    @BindView(R.id.webll)
    LinearLayout mWebll;
    private AgentWeb agentWeb;
    private String link;


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_web;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        link = intent.getStringExtra(Constants.EMAIL);
        String title = intent.getStringExtra(Constants.DATA);
        mWebtoolTv.setText(title);
        //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
// 使用默认进度条
// 使用默认进度条颜色
        agentWeb = AgentWeb.with(this)
                //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .setAgentWebParent(mWebll, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor()// 使用默认进度条颜色
                .createAgentWeb()
                .ready()
                .go(link);

        setSupportActionBar(mWebtoolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(2, 2, 2, "分享");
        menu.add(3, 3, 3, "收藏");
        menu.add(4, 4, 4, "浏览器打开");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:

                break;
            case 3:

                break;
            case 4:
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void initListener() {
        mWebtoolImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
