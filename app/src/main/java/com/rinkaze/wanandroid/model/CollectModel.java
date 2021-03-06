package com.rinkaze.wanandroid.model;

import android.text.TextUtils;
import android.util.Log;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.MyCollectBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

/**
 * Created by 灵风 on 2019/5/23.
 */

public class CollectModel extends BaseModel {
    private static final String TAG = "CollectModel";
    private String psw;
    private String name;

    public void getCollectData(int page, final ResultCallBack<MyCollectBean> callBack){
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .getCollectData(name, psw,page)
                .compose(RxUtils.<MyCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyCollectBean>() {
                    @Override
                    public void error(String msg) {
                        Logger.logD(TAG,msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MyCollectBean myCollectBean) {
                        if (myCollectBean != null){
                            if (myCollectBean.getErrorCode() == WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess(myCollectBean);
                            }else {
                                callBack.onFail(myCollectBean.getErrorMsg());
                            }
                        }
                    }
                });
    }

    public void disCollect(int id, int originId, final ResultCallBack<String> resultCallBack) {
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .disCollect(id,originId,name,psw)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e="+msg );
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        if (jsonObject != null){
                            resultCallBack.onSuccess("已取消收藏");
                        }
                    }
                });
    }
}
