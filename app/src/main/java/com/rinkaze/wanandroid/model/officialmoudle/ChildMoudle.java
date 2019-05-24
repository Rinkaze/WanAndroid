package com.rinkaze.wanandroid.model.officialmoudle;

import android.util.Log;

import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

public class ChildMoudle extends BaseModel {


    private static final String TAG = "ChildMoudle";
    private String name;
    private String psw;

    public void getData(int id, int page, final ResultCallBack<FeedArticleListData> callBack) {

        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getWxSumData(id, page)
                .compose(RxUtils.<FeedArticleListData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>() {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        callBack.onSuccess(feedArticleListData);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

    public void getCollect(int id, final ResultCallBack<String> callBack) {
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getCollect(name,psw,id)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {


                    @Override
                    public void onNext(JSONObject jsonObject) {
                        try {
                            int errorCode = jsonObject.getInt("errorCode");
                            Log.e(TAG, "onNext: " + errorCode);
                            if (errorCode == WanAndroidApi.SUCCESS_CODE) {
                                callBack.onSuccess("收藏成功");

                            } else {
                                callBack.onFail(jsonObject.getString("errorMsg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }


    public void getDisCollect(int id, int originId,String name,String psw, final ResultCallBack<String> callBack) {
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.disCollect(id,originId,name,psw)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        try {
                            int errorCode = jsonObject.getInt("errorCode");
                            Log.e(TAG, "onNext: " + errorCode);
                            if (errorCode == WanAndroidApi.SUCCESS_CODE) {
                                callBack.onSuccess("收藏成功");

                            } else {
                                callBack.onFail(jsonObject.getString("errorMsg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }

}
