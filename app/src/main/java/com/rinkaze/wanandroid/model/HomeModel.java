package com.rinkaze.wanandroid.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.CollectBean;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.EveryWhereApi;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {

    private String psw;
    private String name;

    public void getHomeInit(int num, final ResultCallBack<HomeBean> resultCallBack){
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getHomeInit(num)
                .compose(RxUtils.<HomeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean!=null){
                            resultCallBack.onSuccess(homeBean);
                        }else {
                            resultCallBack.onFail("请求失败");
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
    public void getBanner(final ResultCallBack<HomeBanner>resultCallBack){
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getBanninit()
                .compose(RxUtils.<HomeBanner>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBanner>() {
                    @Override
                    public void onNext(HomeBanner homeBanner) {
                        if (homeBanner!=null){
                            resultCallBack.onSuccess(homeBanner);
                        }else {
                            resultCallBack.onFail("请求失败");
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }

    private static final String TAG = "HomeModel";
    public void getLike(int id, final ResultCallBack<String>resultCallBack){
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getCollect(name,psw,id)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        resultCallBack.onSuccess(s.toString());
                    }

                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e="+msg );
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
    public void getDisLike(int disid, final ResultCallBack<String>resultCallBack){
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getDisCollect(name,psw,disid)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        if (s != null){
                            resultCallBack.onSuccess("");
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

}
