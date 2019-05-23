package com.rinkaze.wanandroid.view;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.net.ResultCallBack;

public interface HomeView extends BaseMvpView {
    void onSuccess1(HomeBean bean);
    void onFail1(String msg1);
    void onSuccess2(HomeBanner bean);
    void onFail2(String msg2);
    void onCollectSucceed(String bean);
    void onCollectFalse(String msg2);
}
