package com.rinkaze.wanandroid.view.officialview;

import com.rinkaze.wanandroid.bean.official.WxAuthor;
import com.rinkaze.wanandroid.base.BaseMvpView;

public interface OfficialView extends BaseMvpView {
    void getSuccess(WxAuthor wxAuthor);
    void getFailed(String msg);
}
