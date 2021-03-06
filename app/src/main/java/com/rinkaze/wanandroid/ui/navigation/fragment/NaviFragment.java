package com.rinkaze.wanandroid.ui.navigation.fragment;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.just.library.AgentWeb;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.presenter.NaviPresenter;
import com.rinkaze.wanandroid.ui.navigation.activity.AgentWebActivity;
import com.rinkaze.wanandroid.ui.navigation.adapter.NaviRecAdapter;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;
import com.rinkaze.wanandroid.view.NaviView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

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
    private FragmentManager manager;
    private AgentWeb agentWeb;

    @Override
    protected NaviPresenter initPresenter() {
        return new NaviPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi_layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        datalist = new ArrayList<>();
        //tab的监听
        tablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                recyc.scrollToPosition(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });

        //配置Recyclerview
        final LinearLayoutManager mManager = new LinearLayoutManager(getContext());
        recyc.setLayoutManager(mManager);
        adapter = new

                NaviRecAdapter(getContext());
        recyc.setAdapter(adapter);
        //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度(这里固定高度50dp)，用于recyclerView的最后一个item view填充高度
        recyc.setOnScrollChangeListener(new View.OnScrollChangeListener()

        {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                tablayout.setTabSelected(mManager.findFirstVisibleItemPosition());
//                tablayout.setVerticalScrollbarPosition(mManager.findFirstVisibleItemPosition());
            }
        });
        adapter.setListener(new NaviRecAdapter.OnItenClickListener() {
            @Override
            public void listener(String link, String title, String author) {
                Intent intent = new Intent(getContext(), AgentWebActivity.class);
                intent.putExtra("author", author);
                intent.putExtra("title", title);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });
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

    public void scrollTop() {
        if (recyc != null) {
            recyc.smoothScrollToPosition(0);
        }
    }
}
