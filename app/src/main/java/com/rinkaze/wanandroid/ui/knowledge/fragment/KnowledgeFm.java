package com.rinkaze.wanandroid.ui.knowledge.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;
import com.rinkaze.wanandroid.presenter.KnowledgeP;
import com.rinkaze.wanandroid.ui.knowledge.activity.KnowledgeActivity;
import com.rinkaze.wanandroid.ui.knowledge.adapter.KnowledgeViewAdapter;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.view.KnowledgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeFm extends BaseFragment<KnowledgeView, KnowledgeP> implements KnowledgeView {
    private static final String TAG = "KnowledgeFm";
    @BindView(R.id.knowledge_review)
    RecyclerView mKnowledgeReview;
    private List<KnowledgeHierarchyData.DataBean> mKnowledgeList=new ArrayList<>();
    private KnowledgeViewAdapter mAdapter;
    private List<KnowledgeHierarchyData.DataBean> data1;

    @Override
    protected KnowledgeP initPresenter() {
        return new KnowledgeP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        mPresenter.onKnowledgePresentrt();
        mKnowledgeReview.setLayoutManager(new LinearLayoutManager(getContext()));
        mKnowledgeReview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mAdapter = new KnowledgeViewAdapter(getContext(), mKnowledgeList);
        mKnowledgeReview.setAdapter(mAdapter);


    }

    @Override
    public void KnowledgeData(final KnowledgeHierarchyData data) {
        data1 = data.getData();
        mKnowledgeList.addAll(data1);
        mAdapter.setmKnowledgeList(mKnowledgeList);


        mAdapter.setOnKnowledgeClick(new KnowledgeViewAdapter.onKnowledgeClick() {
            @Override
            public void onKnowledgeItemClick(KnowledgeHierarchyData.DataBean dataBean, int postion) {
                Intent intent = new Intent(getContext(), KnowledgeActivity.class);
                intent.putExtra(Constants.TITLE, data1.get(postion).getName());
                intent.putExtra(Constants.DESC,postion);
                startActivity(intent);
            }
        });
    }
    @Override
    public void ErrorData(String e) {
        Logger.logD("KnowledgeFm",e);
    }
}
