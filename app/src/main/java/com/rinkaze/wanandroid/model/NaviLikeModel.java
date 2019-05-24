package com.rinkaze.wanandroid.model;

import android.util.Log;

import com.google.gson.JsonObject;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class NaviLikeModel extends BaseModel {
    private static final String TAG = "NaviLikeModel";
    private String psw;
    private String userName;

    public void initNaviLike(String name, String author, String link, final ResultCallBack<String> resultCallBack) {
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        userName = (String) SpUtil.getParam(Constants.USERNAME, "");
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        Observable<JSONObject> naviLike = apiserver.getNaviLike(userName, psw, name, author, link);
        naviLike.compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        resultCallBack.onSuccess("收藏成功");
                    }

                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e=" + msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
