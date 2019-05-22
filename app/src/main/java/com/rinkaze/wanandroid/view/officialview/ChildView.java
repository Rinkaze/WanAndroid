package com.rinkaze.wanandroid.view.officialview;

import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.base.BaseMvpView;

public interface ChildView extends BaseMvpView {

    void getSuccess(FeedArticleListData feedArticleListData);
    void getFailed(String msg);
}
