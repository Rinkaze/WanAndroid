package com.rinkaze.wanandroid.ui.main.activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

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
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private RecCollectAdapter adapter;
    private int page = 0;
    private ArrayList<MyCollectBean.DataEntity.DatasEntity> list;
    private int position;

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
    public void disCollect(String msg) {
        list.remove(position);
        adapter.setList(list);
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);
        mSrl.finishLoadMore();
        mSrl.finishRefresh();
    }

    @Override
    protected void initView() {
        mToolBar.setTitle("我的收藏");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setNavigationIcon(R.mipmap.back_white);
        list = new ArrayList<>();
        mRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecCollectAdapter(this);
        mRecView.setAdapter(adapter);
        mRecView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
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
        adapter.setOnCollectListener(new RecCollectAdapter.OnCollectListener() {
            @Override
            public void disCollect(int position) {
                CollectActivity.this.position = position;
                mPresenter.disCollect(list.get(position).getId(),list.get(position).getOriginId());
            }
        });
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
