package com.rinkaze.wanandroid.ui.main.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.bean.MyCollectBean;
import com.rinkaze.wanandroid.presenter.CollectPresenter;
import com.rinkaze.wanandroid.ui.main.Adapter.RecCollectAdapter;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.main.CollectView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class CollectActivity extends BaseActivity<CollectView, CollectPresenter> implements CollectView {

    @BindView(R.id.recView)
    RecyclerView mRecView;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private RecCollectAdapter adapter;
    private int page = 0;
    private ArrayList<MyCollectBean.DataEntity.DatasEntity> list;

    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    public void onSuccess(MyCollectBean.DataEntity collectBean) {
        if (page == 0){
            list.clear();
        }
        list.addAll(collectBean.getDatas());
        adapter.setList(list);
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);
        mSrl.finishLoadMore();
        mSrl.finishRefresh();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        mRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecCollectAdapter(this);
        mRecView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getCollectData(page);
    }

    @Override
    protected void initListener() {
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                initData();
            }
        });
    }
}
