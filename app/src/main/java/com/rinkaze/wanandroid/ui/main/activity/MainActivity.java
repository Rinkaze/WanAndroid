package com.rinkaze.wanandroid.ui.main.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.BaseApp;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.LoginInfo;
import com.rinkaze.wanandroid.model.LoginModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.ui.knowledge.fragment.KnowledgeFm;
import com.rinkaze.wanandroid.ui.main.fragment.MainFragment;
import com.rinkaze.wanandroid.ui.navigation.fragment.NaviFragment;
import com.rinkaze.wanandroid.ui.official.fragment.OfficialFragment;
import com.rinkaze.wanandroid.ui.project.fragment.ProjectFragment;
import com.rinkaze.wanandroid.ui.todo.activity.ToDoActivity;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.utils.UIModeUtil;
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
    private MainFragment mainFragment;
    private KnowledgeFm knowledgeFm;
    private OfficialFragment officialFragment;
    private NaviFragment naviFragment;
    private ProjectFragment projectFragment;

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
            tvLogin.setText((String)SpUtil.getParam(Constants.NAME,"登录"));
        }
        tvLogin.setOnClickListener(this);

    }

    //添加Fragment并默认显示第一个Fragment
    private void initFragment() {
        fragments = new ArrayList<>();
        mainFragment = new MainFragment();
        fragments.add(mainFragment);
        knowledgeFm = new KnowledgeFm();
        fragments.add(knowledgeFm);
        officialFragment = new OfficialFragment();
        fragments.add(officialFragment);
        naviFragment = new NaviFragment();
        fragments.add(naviFragment);
        projectFragment = new ProjectFragment();
        fragments.add(projectFragment);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.fragment_container, fragments.get(0)).commit();
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
        mMainFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (lastPosition) {
                    case MAIN_TYPE:
                        if (mainFragment != null){
                            mainFragment.scrollTop();
                        }
                        break;
                    case KNOWLEDGE_TYPE:
                        if (knowledgeFm != null){
                            knowledgeFm.scrollTop();
                        }
                        break;
                    case WECHAT_TYPE:
                        if (officialFragment != null){
                            officialFragment.scrollTop();
                        }
                        break;
                    case NAVIGATION_TYPE:
                        if (naviFragment != null){
                            naviFragment.scrollTop();
                        }
                        break;
                    case PROJECT_TYPE:
                        if (projectFragment != null){
                            projectFragment.scrollTop();
                        }
                        break;
                }
            }
        });
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_collect:
                        //收藏
                        if ((boolean)SpUtil.getParam(Constants.LOGIN,false)) {
                            startActivity(new Intent(MainActivity.this, CollectActivity.class));
                        }else {
                            ToastUtil.showShort("请先登录");
                            startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),100);
                        }
                        break;
                    case R.id.nav_todo:
                        Intent intent = new Intent(MainActivity.this, ToDoActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_night:
                        //夜间模式
                        setDaiNightMode();
                        break;
                    case R.id.nav_setting:
                        //设置
                        break;
                    case R.id.nav_about:
                        //关于我们
                        startActivity(new Intent(MainActivity.this,MyActivity.class));
                        break;
                    case R.id.nav_logout:
                        //退出登录
                        if ((boolean)SpUtil.getParam(Constants.LOGIN,false)){
                            showLoading();
                            new LoginModel().logout(new ResultCallBack<LoginInfo>() {
                                @Override
                                public void onSuccess(LoginInfo bean) {
                                    SpUtil.setParam(Constants.NAME,"登录");
                                    SpUtil.setParam(Constants.TOKEN,0);
                                    SpUtil.setParam(Constants.LOGIN,false);
                                    SpUtil.setParam(Constants.PASSWORD,"");
                                    SpUtil.setParam(Constants.USERNAME,"");
                                    tvLogin.setText(R.string.login);
                                    ToastUtil.showShort("已退出登录");
                                    hideLoading();
                                }
                                @Override
                                public void onFail(String msg) {
                                    ToastUtil.showShort(msg);
                                }
                            });
                        }
                        break;
                }
                return false;
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
            case R.id.tv_login:
                if (tvLogin.getText().toString().trim().equals("登录"))
                    startActivityForResult(new Intent(this,LoginActivity.class),100);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((boolean)SpUtil.getParam(Constants.LOGIN,false)){
            tvLogin.setText((String)SpUtil.getParam(Constants.NAME,"登录"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == WanAndroidApi.SUCCESS_CODE){
            tvLogin.setText((String)SpUtil.getParam(Constants.NAME,"登录"));
        }
    }
}
