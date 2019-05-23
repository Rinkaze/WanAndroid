package com.rinkaze.wanandroid.ui.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomUrlActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.pro_img)
    ImageView mProImg;
    @BindView(R.id.pro_title)
    TextView mProTitle;
    @BindView(R.id.pro_toolbar)
    Toolbar mProToolbar;
    @BindView(R.id.homurl)
    LinearLayout mHomurl;
    private String link;
    private String title;
    private AgentWeb mAgentWeb;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hom_url;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        link = intent.getStringExtra("link");
        title = intent.getStringExtra("title");
    }

    @Override
    protected void initData() {
        mProTitle.setText(title);
        mProToolbar.setTitle(" ");
        setSupportActionBar(mProToolbar);
        ChromeClientCallbackManager.ReceivedTitleCallback mCallback;
        mCallback = null;
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mHomurl, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .createAgentWeb()
                .ready()
                .go(link);
    }

    @OnClick({R.id.pro_img, R.id.pro_title, R.id.pro_toolbar, R.id.homurl})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pro_img:
                finish();
                break;
            case R.id.pro_title:
                break;
            case R.id.pro_toolbar:
                break;
            case R.id.homurl:
                break;
        }
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
}
