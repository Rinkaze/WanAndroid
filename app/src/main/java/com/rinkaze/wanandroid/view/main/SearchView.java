package com.rinkaze.wanandroid.view.main;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.official.SearchBean;

public interface SearchView extends BaseMvpView {
    void onSuccess(SearchBean bean);
    void onFail(String msg);
}
