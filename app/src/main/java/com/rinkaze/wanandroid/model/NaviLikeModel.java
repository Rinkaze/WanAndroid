package com.rinkaze.wanandroid.model;

import com.google.gson.JsonObject;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class NaviLikeModel extends BaseModel {
    public void initNaviLike(String name, String link, final ResultCallBack<String> resultCallBack){
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        Observable<String> naviLike = apiserver.getNaviLike(name, link);
        naviLike.compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int errorCode = jsonObject.getInt("errorCode");
                            if (errorCode==WanAndroidApi.SUCCESS_CODE){
                                resultCallBack.onSuccess("收藏成功");
                            }else{
                                resultCallBack.onFail(jsonObject.getString("errorMsg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
