package com.rinkaze.wanandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.bean.official.SearchBean;
import com.rinkaze.wanandroid.presenter.officialpresenter.SearchPresenter;
import com.rinkaze.wanandroid.ui.main.Adapter.SearchAdapter;
import com.rinkaze.wanandroid.view.main.SearchView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchView, SearchPresenter> implements SearchView {
    @BindView(R.id.search_back)
    ImageView mSearchBack;
    @BindView(R.id.input)
    EditText mInput;
    @BindView(R.id.searcher)
    ImageView mSearcher;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private HashMap<String, Object> map;

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }


    @Override
    protected void initData() {
        String s = mInput.getText().toString();
        map = new HashMap<>();
        map.put("k",s);
        mPresenter.getSearch(map);
    }

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSuccess(SearchBean bean) {
        final List<SearchBean.DataBean.DatasBean> list = bean.getData().getDatas();
        SearchAdapter searchAdapter = new SearchAdapter(list, SearchActivity.this);
        mRecycler.setAdapter(searchAdapter);
        searchAdapter.setOnitemclick(new SearchAdapter.Onitemclick() {
            @Override
            public void Clickitem(int position) {
                String apkLink = list.get(position).getApkLink();
                Intent intent = new Intent(SearchActivity.this, SearchTextActivity.class);
                intent.putExtra("linkurl",apkLink);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFail(String msg) {

    }


    @OnClick({R.id.search_back, R.id.input, R.id.searcher, R.id.recycler})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_back:
                finish();
                break;
            case R.id.searcher:
                mPresenter.getSearch(map);
                break;
        }
    }
}
