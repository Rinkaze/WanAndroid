package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.LoginInfo;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.Logger;

import io.reactivex.disposables.Disposable;

/**
 * Created by 灵风 on 2019/5/21.
 */

public class LoginModel extends BaseModel {
    private static final String TAG = "LoginModel";

    public void login(String username, String psw, final ResultCallBack<LoginInfo> callBack){
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .login(username,psw)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void error(String msg) {
                        Logger.logD(TAG,msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null){
                            if (loginInfo.getErrorCode() == WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getErrorMsg());
                            }
                        }
                    }
                });
    }

    public void register(String name, String psw, String rePsw, final ResultCallBack<LoginInfo> callBack){
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .register(name,psw,rePsw)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void error(String msg) {
                        Logger.logD(TAG,msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null){
                            if (loginInfo.getErrorCode() == WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getErrorMsg());
                            }
                        }
                    }
                });
    }

    public void logout(final ResultCallBack<LoginInfo> callBack){
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .logout()
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void error(String msg) {
                        Logger.logD(TAG,msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null){
                            if (loginInfo.getErrorCode() == WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getErrorMsg());
                            }
                        }
                    }
                });
    }
}
