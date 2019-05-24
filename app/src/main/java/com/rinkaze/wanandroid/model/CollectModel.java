package com.rinkaze.wanandroid.model;

import android.text.TextUtils;
import android.util.Log;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.MyCollectBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

/**
 * Created by 灵风 on 2019/5/23.
 */

public class CollectModel extends BaseModel {
    private static final String TAG = "CollectModel";

    public void getCollectData(int page, final ResultCallBack<MyCollectBean> callBack){
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .getCollectData(page)
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
                .disCollect(id,originId)
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
                        if (jsonObject!=null){
                            try {

                                int code = jsonObject.getInt("errorCode");
                                if (code == WanAndroidApi.SUCCESS_CODE){
                                    resultCallBack.onSuccess("已取消收藏");
                                }else {
                                    resultCallBack.onFail(jsonObject.getString("errorMsg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
