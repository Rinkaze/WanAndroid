package com.rinkaze.wanandroid.model;

import android.util.Log;

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
    private static final String TAG = "NaviLikeModel";
    public void initNaviLike(String name,String author, String link, final ResultCallBack<String> resultCallBack){
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        Observable<JsonObject> naviLike = apiserver.getNaviLike(name,author,link);
        naviLike.compose(RxUtils.<JsonObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JsonObject>() {
                    @Override
                    public void onNext(JsonObject s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s.toString());
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
                        Log.e(TAG, "error: e="+msg );
                    }
                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
