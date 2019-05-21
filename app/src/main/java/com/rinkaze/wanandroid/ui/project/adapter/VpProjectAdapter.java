package com.rinkaze.wanandroid.ui.project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class VpProjectAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragment;

    public VpProjectAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.mFragment = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
