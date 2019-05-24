package com.rinkaze.wanandroid.model;

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

import io.reactivex.disposables.Disposable;

public class KnWebModel extends BaseModel {

    private String psw;
    private String name;

    public void onKnWebModel(String title, String author, String link, final ResultCallBack<String> callBack){
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getNaviLike(name,psw,title,author,link)
                .compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int code = jsonObject.getInt("errorCode");
                            if (code==WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess("收藏成功");
                            }else {
                                callBack.onSuccess("收藏失败");
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
