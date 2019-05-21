package com.rinkaze.wanandroid.ui.project.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.bean.ProjectClassBean;
import com.rinkaze.wanandroid.presenter.ProjectPresenter;
import com.rinkaze.wanandroid.ui.project.adapter.VpProjectAdapter;
import com.rinkaze.wanandroid.view.ProjectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<ProjectView, ProjectPresenter> implements ProjectView {


    @BindView(R.id.project_tab)
    TabLayout mTab;
    @BindView(R.id.project_vp)
    ViewPager mVp;
    private ArrayList<Fragment> mFragments;
    private VpProjectAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }



    @Override
    protected ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setData(ProjectClassBean bean) {
        mFragments = new ArrayList<>();
        List<ProjectClassBean.DataBean> list = bean.getData();
        for (int i = 0; i < list.size(); i++) {
            mFragments.add(new ProjectListFragment(list.get(i).getId()));
            mTab.addTab(mTab.newTab().setText(list.get(i).getName()));
        }
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));

        mAdapter = new VpProjectAdapter(getChildFragmentManager(), mFragments);
        mVp.setAdapter(mAdapter);
    }
}
