package com.rinkaze.wanandroid.ui.navigation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;
import com.rinkaze.wanandroid.ui.navigation.fragment.Fragment_List;

import java.util.ArrayList;
import java.util.List;

public class NaviVpAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<Navi_Tab_Bean.DataEntity> list=new ArrayList<>();
    public NaviVpAdapter(FragmentManager fm, ArrayList<Fragment> fragments,ArrayList<Navi_Tab_Bean.DataEntity> list) {
        super(fm);
        this.fragments=fragments;
        this.list=list;
       /* for (int i = 0; i < list.size(); i++) {
            fragments.add(Fragment_List.newInstance(i));
        }*/
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getArticles().get(position).getTitle();
    }
}
