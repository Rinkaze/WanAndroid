package com.rinkaze.wanandroid.ui.official.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.rinkaze.wanandroid.bean.official.WxAuthor;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.presenter.officialpresenter.OfficialPresenter;
import com.rinkaze.wanandroid.ui.official.adapter.OfficialViewPagerAdapter;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.view.officialview.OfficialView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfficialFragment extends BaseFragment<OfficialView, OfficialPresenter> implements OfficialView {


    private static final String TAG ="OfficialFragment" ;
    @BindView(R.id.official_tab)
    TabLayout mOfficialTab;
    @BindView(R.id.official_vp)
    ViewPager mOfficialVp;

    private ArrayList<Fragment> list;
    private ArrayList<String> title;
    private OfficialViewPagerAdapter adapter;

    @Override
    protected OfficialPresenter initPresenter() {
        return new OfficialPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_official;
    }

    @Override
    protected void initView() {
        super.initView();
        list = new ArrayList<>();
        title = new ArrayList<>();
        adapter = new OfficialViewPagerAdapter(getFragmentManager(),list,title);

    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter.getAutherData();
    }

    @Override
    public void getSuccess(WxAuthor wxAuthor) {
        List<WxAuthor.DataBean> data = wxAuthor.getData();

        for (WxAuthor.DataBean datum : data) {
            Bundle bundle = new Bundle();
            String name = datum.getName();
            title.add(name);

            int id = datum.getId();
            Log.e(TAG, "getSuccess: "+id );
            bundle.putInt("id",id);
            ChildFragment childFragment = new ChildFragment();
            childFragment.setArguments(bundle);
            list.add(childFragment);
            Log.e(TAG, "getSuccess: "+title.size() );
        }

        mOfficialVp.setAdapter(adapter);
        mOfficialTab.setupWithViewPager(mOfficialVp);
    }

    @Override
    public void getFailed(String msg) {
        Logger.logD(TAG,msg);
    }

    public void scrollTop() {
        if (list != null && mOfficialVp != null){
            ChildFragment childFragment = (ChildFragment) list.get(mOfficialVp.getCurrentItem());
            childFragment.scrollTop();
        }
    }
}
