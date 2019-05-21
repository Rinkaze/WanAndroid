package com.rinkaze.wanandroid.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.ui.project.fragment.ProjectFragment;
import com.rinkaze.wanandroid.view.EmptyView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

public class MainActivity extends BaseActivity<EmptyView,EmptyPresenter> implements EmptyView{

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}





