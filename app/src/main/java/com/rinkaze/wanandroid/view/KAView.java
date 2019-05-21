package com.rinkaze.wanandroid.view;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.FeedArticleListData;

public interface KAView extends BaseMvpView {
    void KAData(FeedArticleListData data);
    void ErrorData(String e);
}
