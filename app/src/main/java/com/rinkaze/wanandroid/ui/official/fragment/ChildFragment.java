package com.rinkaze.wanandroid.ui.official.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.presenter.officialpresenter.ChildPresenter;
import com.rinkaze.wanandroid.ui.official.activity.WebViewActivity;
import com.rinkaze.wanandroid.ui.official.adapter.OfficialChildAdapter;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.officialview.ChildView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends BaseFragment<ChildView, ChildPresenter> implements ChildView {

    private static final String TAG = "ChildFragment";
    @BindView(R.id.child_rlv)
    RecyclerView mChildRlv;
    Unbinder unbinder;
    @BindView(R.id.official_smart)
    SmartRefreshLayout mOfficialSmart;

    Unbinder unbinder1;
    private int page = 1;
    private ArrayList<FeedArticleListData.DataBean.DatasBean> list;
    private OfficialChildAdapter adapter;
    private int id;

    public ChildFragment() {
        // Required empty public constructor
    }


    @Override
    protected ChildPresenter initPresenter() {
        return new ChildPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_child;
    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        adapter = new OfficialChildAdapter(getActivity());
        id = getArguments().getInt("id");

        Log.e(TAG, "initView: " + id);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getArtData(id, page);
        mOfficialSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getArtData(id, page);
                refreshLayout.finishRefresh();
            }
        });

        mOfficialSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getArtData(id, page);
                refreshLayout.finishLoadMore();
            }
        });
        mOfficialSmart.setEnableRefresh(true);
        mOfficialSmart.setEnableLoadMore(true);
        mOfficialSmart.finishRefresh();
        mOfficialSmart.finishLoadMore();
    }


    @Override
    public void getSuccess(FeedArticleListData feedArticleListData) {

        List<FeedArticleListData.DataBean.DatasBean> datas = feedArticleListData.getData().getDatas();
        list.addAll(datas);

        mChildRlv.setAdapter(adapter);
        mChildRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setList(list);


    }


    @Override
    protected void initListener() {
        super.initListener();
        adapter.setClick(new OfficialChildAdapter.setOnClick() {
            @Override
            public void setClick(View v, int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                FeedArticleListData.DataBean.DatasBean datasBean = list.get(position);
                String link = datasBean.getLink();
                String author = datasBean.getAuthor();
                intent.putExtra("url", link);
                intent.putExtra("name", author);
                startActivity(intent);
            }
        });

        adapter.setLike(new OfficialChildAdapter.setClickLike() {
            @Override
            public void setLike(int position) {
                ToastUtil.showShort("已收藏");
            }

            @Override
            public void remove(int id) {

            }
        });

    }

    @Override
    public void getFailed(String msg) {
        Logger.logD(TAG, msg);
    }




}
