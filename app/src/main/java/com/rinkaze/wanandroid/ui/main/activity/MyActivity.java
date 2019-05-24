package com.rinkaze.wanandroid.ui.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.MyPresenter;
import com.rinkaze.wanandroid.view.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyActivity extends BaseActivity<MyView, MyPresenter> implements MyView {


    @BindView(R.id.my_back)
    ImageView mMyBack;
    @BindView(R.id.url)
    TextView mUrl;
    @BindView(R.id.githup)
    TextView mGithup;

    @Override
    protected MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView() {
        super.initView();

    }




    @OnClick({R.id.my_back, R.id.url, R.id.githup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_back:
                finish();
                break;
            case R.id.url:
                String text = mUrl.getText().toString();
                Uri parse = Uri.parse(text);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, parse);
                startActivity(intent1);
                break;
            case R.id.githup:
                String git = "https://github.com/iceCola7/WanAndroid";
                Uri githup = Uri.parse(git);
                Intent intent2 = new Intent(Intent.ACTION_VIEW, githup);
                startActivity(intent2);
                break;
        }
    }
}
