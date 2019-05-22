package com.rinkaze.wanandroid.view;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;

public interface NaviView extends BaseMvpView {
    void initSuccess(Navi_Tab_Bean navibean);
    void initFani(String msg);
}
