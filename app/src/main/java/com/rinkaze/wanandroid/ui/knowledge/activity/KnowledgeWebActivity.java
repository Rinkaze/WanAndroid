package com.rinkaze.wanandroid.ui.knowledge.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.presenter.KnWebPresenter;
import com.rinkaze.wanandroid.ui.main.activity.LoginActivity;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.EmptyView;
import com.rinkaze.wanandroid.view.KnWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeWebActivity extends BaseActivity<KnWebView, KnWebPresenter> implements KnWebView {


    private static final String TAG = "KnowledgeWebActivity";
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
    private String title;
    private String anthor;


    @Override
    protected KnWebPresenter initPresenter() {
        return new KnWebPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_web;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mWebtoolbar.setTitle("");
        link = intent.getStringExtra(Constants.EMAIL);
        title = intent.getStringExtra(Constants.DATA);
        anthor = intent.getStringExtra(Constants.DESC);
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
        menu.add(4, 4, 4, "使用浏览器打开");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT, title + ":" + link);
                startActivity(intent1);
                break;
            case 3:
                boolean param = (boolean) SpUtil.getParam(Constants.LOGIN, false);
                if (param){
                    mPresenter.initNaviLike(title,anthor,link);
                }else {
                    startActivityForResult(new Intent(KnowledgeWebActivity.this,LoginActivity.class),100);
                    ToastUtil.showShort("请先登录");
                }
                break;
            case 4:
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == WanAndroidApi.SUCCESS_CODE){
            mPresenter.initNaviLike(title,anthor,link);
        }
    }

    @Override
    public void KnWebData(String s) {
        ToastUtil.showLong(s);
    }

    @Override
    public void ErrorData(String e) {
        Log.d(TAG, "ErrorData: "+e);
    }
}
