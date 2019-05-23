package com.rinkaze.wanandroid.ui.knowledge.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.presenter.KAPresenter;
import com.rinkaze.wanandroid.ui.knowledge.activity.KnowledgeWebActivity;
import com.rinkaze.wanandroid.ui.knowledge.adapter.KAViewAdapter;
import com.rinkaze.wanandroid.ui.main.activity.LoginActivity;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.KAView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KAFragment extends BaseFragment<KAView, KAPresenter> implements KAView {

    private static final String TAG = "KAFragment";
    @BindView(R.id.review)
    RecyclerView mReview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout mSmartrefreshlayout;
    private int pag = 1;
    private List<FeedArticleListData.DataBean.DatasBean> mList = new ArrayList<>();
    private KAViewAdapter mAdapter;
    private int cid;

    @Override
    protected KAPresenter initPresenter() {
        return new KAPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ka;
    }

    @Override
    public void KAData(final FeedArticleListData data) {
        final List<FeedArticleListData.DataBean.DatasBean> datas = data.getData().getDatas();
        mList.addAll(datas);
        mAdapter.setmList(mList);


        mAdapter.setOnKAClick(new KAViewAdapter.onKAClick() {
            @Override
            public void setonKAItemClick(FeedArticleListData.DataBean.DatasBean datasBean, int postion) {
                Intent intent = new Intent(getContext(), KnowledgeWebActivity.class);
                intent.putExtra(Constants.EMAIL, datasBean.getLink());
                intent.putExtra(Constants.DATA, datasBean.getTitle());
                intent.putExtra(Constants.DESC,datasBean.getAuthor());
                startActivity(intent);
            }
        });

        mAdapter.setLike(new KAViewAdapter.OnItenClickListener() {
            @Override
            public void like(int id) {
                boolean param = (boolean) SpUtil.getParam(Constants.LOGIN, false);
                if (param){
                    ToastUtil.showLong("已收藏");
                }else {
                    startActivityForResult(new Intent(getContext(),LoginActivity.class),100);
                    ToastUtil.showShort("请先登录");
                }
            }
            @Override
            public void remove(int id) {
                mPresenter.initDeleteData(id);
                ToastUtil.showLong("取消收藏");
            }
        });
    }

    @Override
    public void ErrorData(String e) {
        Logger.logD(TAG, e);
    }

    @Override
    public void KACancelData(String s) {
        ToastUtil.showLong(s);
    }
    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        cid = arguments.getInt(Constants.DATA);
        mPresenter.initPresenter(pag, cid);
        mReview.setLayoutManager(new LinearLayoutManager(getContext()));
        mReview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new KAViewAdapter(getContext(), mList);
        mReview.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mSmartrefreshlayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载
                pag++;
                mPresenter.initPresenter(pag, cid);
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                mPresenter.initPresenter(pag, cid);
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }
        });
        mSmartrefreshlayout.setEnableLoadMore(true);
        mSmartrefreshlayout.setEnableRefresh(true);
        mSmartrefreshlayout.finishLoadMore();
        mSmartrefreshlayout.finishRefresh();
    }
}
