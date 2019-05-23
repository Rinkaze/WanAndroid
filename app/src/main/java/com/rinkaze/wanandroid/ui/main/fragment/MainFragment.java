package com.rinkaze.wanandroid.ui.main.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.presenter.HomePresenter;
import com.rinkaze.wanandroid.ui.main.Adapter.HomeAdapter;
import com.rinkaze.wanandroid.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private View view;
    private int num;
    private List<HomeBean.DataBean.DatasBean> listitem;
    private List<HomeBanner.DataBean> listBann;

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mPresenter.getHomeInit(num);
    }

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initListener() {
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                num++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                num=1;
                initData();
            }
        });
    }



    @Override
    public void onSuccess1(HomeBean bean) {
        listitem = bean.getData().getDatas();
        HomeAdapter homeAdapter = new HomeAdapter(listitem, listBann, getContext());
        mRecycler.setAdapter(homeAdapter);
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
    }

    @Override
    public void onFail1(String msg1) {
        Log.e("zak", "onFail1: 条目数据"+msg1 );
    }

    @Override
    public void onSuccess2(HomeBanner bean) {
        listBann = bean.getData();
    }

    @Override
    public void onFail2(String msg2) {
        Log.e("zak", "onFail1: 条目数据"+msg2 );
    }
}
