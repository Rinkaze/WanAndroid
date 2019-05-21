package com.rinkaze.wanandroid.ui.main.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.ui.knowledge.adapter.FmAdapter;
import com.rinkaze.wanandroid.ui.knowledge.fragment.EnFragment;
import com.rinkaze.wanandroid.ui.knowledge.fragment.KnowledgeFm;
import com.rinkaze.wanandroid.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private String [] arr={"知识","我的"};
    private ArrayList<Fragment> list;
    private FmAdapter fmAdapter;


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add(new KnowledgeFm());
        list.add(new EnFragment());
        fmAdapter = new FmAdapter(getSupportFragmentManager(), arr, list);
        mVp.setAdapter(fmAdapter);
        mTab.setupWithViewPager(mVp);
    }
}
