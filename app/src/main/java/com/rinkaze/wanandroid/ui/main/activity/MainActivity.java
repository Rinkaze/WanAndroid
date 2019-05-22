package com.rinkaze.wanandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.ui.main.fragment.MainFragment;
import com.rinkaze.wanandroid.ui.official.fragment.ChildFragment;
import com.rinkaze.wanandroid.ui.project.fragment.ProjectFragment;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView, View.OnClickListener {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mMainFloatingActionBtn;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.nv)
    NavigationView mNv;
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private final int MAIN_TYPE = 0;
    private final int KNOWLEDGE_TYPE = 1;
    private final int WECHAT_TYPE = 2;
    private final int NAVIGATION_TYPE = 3;
    private final int PROJECT_TYPE = 4;
    private int lastPosition = 0;
    private TextView tvLogin;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolBar.setTitle("玩Android");
        mToolBar.setNavigationIcon(null);
        //设置左上角侧滑开关并将颜色改为白色
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, 0, 0);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        initNav();
        initTab();
        initFragment();
    }

    private void initNav() {
        //解决侧滑菜单图标不显示问题
        mNv.setItemIconTintList(null);
        View headerView = mNv.getHeaderView(0);
        tvLogin = headerView.findViewById(R.id.tv_login);
        //判断如果用户已经登陆过，直接显示用户名
        if ((boolean)SpUtil.getParam(Constants.LOGIN,false)){
            tvLogin.setText((String)SpUtil.getParam(Constants.USERNAME,"登录"));
        }
        tvLogin.setOnClickListener(this);
    }

    //添加Fragment并默认显示第一个Fragment
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new Fragment());
        fragments.add(new ChildFragment());
        fragments.add(new Fragment());
        fragments.add(new ProjectFragment());
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.fragment_container, fragments.get(0)).commit();
        mMainFloatingActionBtn.setOnClickListener(this);
    }

    //给Tab栏添加Tab项
    private void initTab() {
        mTab.addTab(mTab.newTab().setText(getResources().getString(R.string.home)).setIcon(getResources().getDrawable(R.drawable.selector_home)));
        mTab.addTab(mTab.newTab().setText(getResources().getString(R.string.knowledge)).setIcon(getResources().getDrawable(R.drawable.selector_knowledge)));
        mTab.addTab(mTab.newTab().setText(getResources().getString(R.string.wechat)).setIcon(getResources().getDrawable(R.drawable.selector_wx_article)));
        mTab.addTab(mTab.newTab().setText(getResources().getString(R.string.navigation)).setIcon(getResources().getDrawable(R.drawable.selector_navigation)));
        mTab.addTab(mTab.newTab().setText(getResources().getString(R.string.project)).setIcon(getResources().getDrawable(R.drawable.selector_project)));
    }

    @Override
    protected void initListener() {
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mToolBar.setTitle("玩Android");
                        switchFragment(MAIN_TYPE);
                        break;
                    case 1:
                        mToolBar.setTitle(R.string.knowledge);
                        switchFragment(KNOWLEDGE_TYPE);
                        break;
                    case 2:
                        mToolBar.setTitle(R.string.wechat);
                        switchFragment(WECHAT_TYPE);
                        break;
                    case 3:
                        mToolBar.setTitle(R.string.navigation);
                        switchFragment(NAVIGATION_TYPE);
                        break;
                    case 4:
                        mToolBar.setTitle(R.string.project);
                        switchFragment(PROJECT_TYPE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * Fragment之间的切换
     *
     * @param type 要切换到哪个fragment
     */
    private void switchFragment(int type) {
        Fragment fragment = fragments.get(type);
        FragmentTransaction tran = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            tran.add(R.id.fragment_container, fragment);
        }
        tran.hide(fragments.get(lastPosition));
        tran.show(fragment);
        tran.commit();
        lastPosition = type;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_floating_action_btn:
                //滑动到页面顶部
                mDl.scrollTo(0, 0);
                break;
            case R.id.tv_login:
                if (tvLogin.getText().toString().trim().equals("登录"))
                startActivityForResult(new Intent(this,LoginActivity.class),100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == WanAndroidApi.SUCCESS_CODE){
            tvLogin.setText((String)SpUtil.getParam(Constants.USERNAME,"登录"));
        }
    }
}
