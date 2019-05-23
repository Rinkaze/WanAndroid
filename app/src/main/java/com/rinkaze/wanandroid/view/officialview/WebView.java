package com.rinkaze.wanandroid.view.officialview;

import com.rinkaze.wanandroid.base.BaseMvpView;

public interface WebView extends BaseMvpView {
    void getSuccess(String s);
    void getFailed(String msg);
}
