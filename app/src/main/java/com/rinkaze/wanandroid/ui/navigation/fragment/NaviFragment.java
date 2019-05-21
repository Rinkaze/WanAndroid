package com.rinkaze.wanandroid.ui.navigation.fragment;


import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.presenter.NaviPresenter;
import com.rinkaze.wanandroid.ui.navigation.adapter.NaviRecAdapter;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;
import com.rinkaze.wanandroid.view.NaviView;
import com.rinkaze.wanandroid.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;

public class NaviFragment extends BaseFragment<NaviView, NaviPresenter> implements NaviView {
    @BindView(R.id.tablayout)
    VerticalTabLayout tablayout;
    @BindView(R.id.recyc)
    RecyclerView recyc;

    private ArrayList<Navi_Tab_Bean.DataEntity> datalist;
    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;
    private View view;
    private NaviRecAdapter adapter;

    @Override
    protected NaviPresenter initPresenter() {
        return new NaviPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_layout;
    }

    @Override
    protected void initView() {
        datalist = new ArrayList<>();
        /*//tab的监听
        tablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                verticalviewpager.setCurrentItem(position);
            }
            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });*/





     //配置Recyclerview
        recyc.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NaviRecAdapter(getContext());
        recyc.setAdapter(adapter);

    }


    @Override
    protected void initData() {
        mPresenter.initNavigation();
    }

    @Override
    public void initSuccess(Navi_Tab_Bean navibean) {
        List<Navi_Tab_Bean.DataEntity> data = navibean.getData();
        datalist.addAll(data);
       adapter.setList((ArrayList<Navi_Tab_Bean.DataEntity>) navibean.getData());
        initTab(data);
    }
    private void initTab(final List<Navi_Tab_Bean.DataEntity> data) {
        //tab适配器
        tablayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new QTabView.TabTitle.Builder()
                        .setContent(data.get(position).getName())
                        .setTextColor(Color.BLUE, Color.BLACK)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
    }

    @Override
    public void initFani(String msg) {

    }
}
