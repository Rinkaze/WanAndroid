package com.rinkaze.wanandroid.ui.official.activity;




import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.officialpresenter.WebPresenter;
import com.rinkaze.wanandroid.view.officialview.WebView;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity<WebView, WebPresenter> implements WebView {


    @BindView(R.id.container)
    RelativeLayout mContainer;
    @BindView(R.id.web_back)
    ImageView webBack;
    @BindView(R.id.web_title)
    TextView webTitle;
    @BindView(R.id.web_toolbar)
    Toolbar webToolbar;
    private AgentWeb agentWeb;
    private String url;

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
        url = getIntent().getStringExtra("url");
        final String name = getIntent().getStringExtra("name");
        ChromeClientCallbackManager.ReceivedTitleCallback mCallback;
        mCallback = null;
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContainer, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("*/*");
                intent1.putExtra(Intent.EXTRA_STREAM, url);
                startActivity(Intent.createChooser(intent1, "Share to..."));
                break;
            case 2:

                break;
            case 3:
                Uri parse = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,parse);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);

    }






}
