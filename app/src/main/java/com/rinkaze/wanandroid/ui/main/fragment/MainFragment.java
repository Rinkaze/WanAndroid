package com.rinkaze.wanandroid.ui.main.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.presenter.HomePresenter;
import com.rinkaze.wanandroid.ui.main.Adapter.HomeAdapter;
import com.rinkaze.wanandroid.ui.main.activity.HomUrlActivity;
import com.rinkaze.wanandroid.ui.main.activity.LoginActivity;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private int num = 0;
    private List<HomeBean.DataBean.DatasBean> listitem;
    private List<HomeBanner.DataBean> listBann;
    private HomeAdapter homeAdapter;

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
        mPresenter.getBannerData();
        mPresenter.getHomeInit(num);
    }

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        listitem = new ArrayList<>();
        listBann = new ArrayList<>();
        homeAdapter = new HomeAdapter(getContext());
        mRecycler.setAdapter(homeAdapter);
    }

    @Override
    protected void initListener() {
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                num++;
                mPresenter.getHomeInit(num);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                num = 0;
                initData();
            }
        });

        homeAdapter.setOnItemUrl(new HomeAdapter.OnItemUrl() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), HomUrlActivity.class);
                intent.putExtra("link", listitem.get(position).getLink());
                intent.putExtra("title", listitem.get(position).getTitle());
                startActivity(intent);
            }

            @Override
            public void setLike(int position) {
                if ((boolean) SpUtil.getParam(Constants.LOGIN, false)) {
                    mPresenter.Like(listitem.get(position).getId());
                } else {
                    ToastUtil.showShort("请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }

            @Override
            public void setDislike(int id) {
                if ((boolean) SpUtil.getParam(Constants.LOGIN, false)) {
                    mPresenter.Dislike(listitem.get(id).getId());
                } else {
                    ToastUtil.showShort("请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });
    }


    @Override
    public void onSuccess1(HomeBean bean) {
        if (num == 0){
            listitem.clear();
        }
        listitem.addAll(bean.getData().getDatas());
        homeAdapter.setListitem(listitem);
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
    }

    @Override
    public void onFail1(String msg1) {
        Log.e("zak", "onFail1: 条目数据" + msg1);
    }

    @Override
    public void onSuccess2(HomeBanner bean) {
        listBann = bean.getData();
        homeAdapter.setListBann(listBann);
    }

    @Override
    public void onFail2(String msg2) {
        Log.e("zak", "onFail1: 条目数据" + msg2);
    }

    @Override
    public void onCollectSucceed(String bean) {
        ToastUtil.showShort(bean);
    }

    @Override
    public void onCollectFalse(String msg2) {
        ToastUtil.showShort(msg2);
    }

    //返回RecyclerView顶部
    public void scrollTop() {
        if (mRecycler != null) {
            mRecycler.smoothScrollToPosition(0);
        }
    }


}
