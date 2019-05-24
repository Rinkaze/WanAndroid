package com.rinkaze.wanandroid.ui.official.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.presenter.officialpresenter.WebPresenter;
import com.rinkaze.wanandroid.ui.main.activity.LoginActivity;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.officialview.WebView;

import java.net.URI;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity<WebView, WebPresenter> implements WebView {

    @BindView(R.id.container)
    RelativeLayout mContainer;
    @BindView(R.id.web_back)
    ImageView mWebBack;
    @BindView(R.id.web_title)
    TextView webTitle;
    @BindView(R.id.web_toolbar)
    Toolbar webToolbar;
    private AgentWeb agentWeb;
    private String url;
    private ActionBar actionBar;

    private String author;
    private String title;

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

        url = getIntent().getStringExtra("link");

        author = getIntent().getStringExtra("author");
        title = getIntent().getStringExtra("title");


        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContainer, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(android.webkit.WebView view, String title) {
                        webTitle.setText(title);
                    }
                })
                .createAgentWeb()
                .ready()
                .go(url);

    }

    @Override
    protected void initView() {
        super.initView();
        webToolbar.setLogo(null);
        webToolbar.setSubtitle("");
        webToolbar.setTitle("");
        setSupportActionBar(webToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "分享");
        menu.add(1, 2, 1, "收藏");
        menu.add(1, 3, 1, "用浏览器打开");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void initListener() {
        super.initListener();
        mWebBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(intent1, url));
                break;
            case 2:
                boolean param = (boolean) SpUtil.getParam(Constants.LOGIN, false);
                String name = (String) SpUtil.getParam(Constants.USERNAME, "");
                if (param) {
                    mPresenter.initNaviLike(title, author, url);
                } else {
                    startActivityForResult(new Intent(WebViewActivity.this, LoginActivity.class), 100);
                    ToastUtil.showShort("请先登录");
                }
                break;
            case 3:
                Uri parse = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, parse);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == WanAndroidApi.SUCCESS_CODE) {
            mPresenter.initNaviLike(title, author, url);
        }
    }


    @Override
    public void getSuccess(String s) {
        ToastUtil.showShort(s);
    }

    @Override
    public void getFailed(String msg) {

    }
}
