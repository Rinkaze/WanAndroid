package com.rinkaze.wanandroid.ui.project.fragment;




import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.bean.ProjectListBean;
import com.rinkaze.wanandroid.presenter.ProjectClassPresenter;
import com.rinkaze.wanandroid.ui.project.activity.ProSubActivity;
import com.rinkaze.wanandroid.ui.project.adapter.RlvProjectClassifyAdapter;
import com.rinkaze.wanandroid.view.ProjectClassifyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ProjectListFragment extends BaseFragment<ProjectClassifyView, ProjectClassPresenter> implements ProjectClassifyView {
    @BindView(R.id.projectlist_rece)
    RecyclerView mRlv;
    @BindView(R.id.srl)
    SmartRefreshLayout mRefreshLayout;
    private int cid;
    private int page = 1;
    private ArrayList<ProjectListBean.DataBean.DatasBean> mList;
    private RlvProjectClassifyAdapter mAdapter;

    public ProjectListFragment(int id) {
        this.cid = id;
    }

    @Override
    protected ProjectClassPresenter initPresenter() {
        return new ProjectClassPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    public void setData(ProjectListBean bean) {
        mList.addAll(bean.getData().getDatas());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        mPresenter.getData(page,cid);
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRlv.setLayoutManager(manager);
        mList = new ArrayList<>();
        mAdapter = new RlvProjectClassifyAdapter(getActivity(),mList);
        mRlv.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mList.clear();
                initData();
                mAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(new RlvProjectClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(),ProSubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("link",mList.get(position).getLink());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }
}
