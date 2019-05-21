package com.rinkaze.wanandroid.view.main;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.LoginInfo;

/**
 * Created by 灵风 on 2019/5/21.
 */

public interface LoginView extends BaseMvpView {
    void onSuccess(LoginInfo loginInfo);
    void onFail(String msg);
}
