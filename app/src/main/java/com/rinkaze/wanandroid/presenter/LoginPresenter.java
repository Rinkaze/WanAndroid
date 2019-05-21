package com.rinkaze.wanandroid.presenter;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.LoginInfo;
import com.rinkaze.wanandroid.model.LoginModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.main.LoginView;

/**
 * Created by 灵风 on 2019/5/21.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements ResultCallBack<LoginInfo> {

    private LoginModel model;

    @Override
    protected void initModel() {
        model = new LoginModel();
        mModels.add(model);
    }

    public void login(String name,String psw){
        model.login(name,psw,this);
    }

    public void register(String name,String psw,String rePsw){
        model.register(name,psw,rePsw,this);
    }

    @Override
    public void onSuccess(LoginInfo bean) {
        if (mMvpView != null){
            mMvpView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        if (mMvpView != null){
            mMvpView.onFail(msg);
        }
    }
}
