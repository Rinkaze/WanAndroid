package com.rinkaze.wanandroid.ui.main.activity;

import android.os.Bundle;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.view.EmptyView;

public class MainActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
