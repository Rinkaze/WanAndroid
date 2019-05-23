package com.rinkaze.wanandroid.view;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;

public interface KAView extends BaseMvpView {
    void KAData(FeedArticleListData data);
    void ErrorData(String e);

    //取消收藏
    void KACancelData(String s);

}
