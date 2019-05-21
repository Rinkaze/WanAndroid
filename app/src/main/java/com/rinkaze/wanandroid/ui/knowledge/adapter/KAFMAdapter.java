package com.rinkaze.wanandroid.ui.knowledge.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class KAFMAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mListFM;
    private ArrayList<String> mListSt;

    public KAFMAdapter(FragmentManager fm, ArrayList<Fragment> mListFM, ArrayList<String> mListSt) {
        super(fm);
        this.mListFM = mListFM;
        this.mListSt = mListSt;
    }


    @Override
    public Fragment getItem(int i) {
        return mListFM.get(i);
    }

    @Override
    public int getCount() {
        return mListFM.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListSt.get(position);
    }
}
