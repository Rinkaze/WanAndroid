package com.rinkaze.wanandroid.ui.knowledge.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FmAdapter extends FragmentPagerAdapter {
    private String[] arr;
    private ArrayList<Fragment> list;

    public FmAdapter(FragmentManager fm, String[] arr, ArrayList<Fragment> list) {
        super(fm);
        this.arr = arr;
        this.list = list;
    }


    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position];
    }
}
