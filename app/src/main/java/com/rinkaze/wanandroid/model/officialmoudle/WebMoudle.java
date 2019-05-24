package com.rinkaze.wanandroid.model.officialmoudle;

import android.util.Log;

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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WebMoudle extends BaseModel {
    private static final String TAG ="WebMoudle" ;
    private String psw;
    private String name;

    public void getLikeData(String title,String author, String link, final ResultCallBack<String> callBack){
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        Observable<JSONObject> naviLike = apiserver.getNaviLike(name,psw,title,author, link);
        naviLike.compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        try {
                            int errorCode = s.getInt("errorCode");
                            Log.e(TAG, "onNext: "+errorCode );
                            if (errorCode==WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess("收藏成功");

                            }else{
                                callBack.onFail(s.getString("errorMsg"));
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
