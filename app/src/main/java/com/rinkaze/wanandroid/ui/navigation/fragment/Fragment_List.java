package com.rinkaze.wanandroid.ui.navigation.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.presenter.NaviPresenter;
import com.rinkaze.wanandroid.ui.navigation.adapter.NaviRecAdapter;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;
import com.rinkaze.wanandroid.view.NaviView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_List extends BaseFragment<NaviView, NaviPresenter> implements NaviView {
    @BindView(R.id.navi_rec)
    RecyclerView naviRec;
    private NaviRecAdapter adapter;
    /* String[] array = new String[]{"Android 1", "Android 2", "Android 3",
            "Android 4", "Android 5", "Android 6", "Android 7", "Android 8",
            "Android 9", "Android 10", "Android 11", "Android 12", "Android 13",
            "Android 14", "Android 15", "Android 16"};
*/
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    /* private static final String ARG_SECTION_NUMBER = "section_number";*/

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
   /* public static Fragment_List newInstance(int sectionNumber) {
        Fragment_List fragment = new Fragment_List();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
*/
    public Fragment_List() {

    }

    @Override
    protected NaviPresenter initPresenter() {
        return new NaviPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi2_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        naviRec.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new NaviRecAdapter(getContext());
        naviRec.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initNavigation();
    }

    @Override
    public void initSuccess(final Navi_Tab_Bean navibean) {
        List<Navi_Tab_Bean.DataEntity> data = navibean.getData();
        adapter.setList((ArrayList<Navi_Tab_Bean.DataEntity>) data);
    }

    @Override
    public void initFani(String msg) {

    }
}
