package com.rinkaze.wanandroid.model.officialmoudle;

import android.util.Log;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WebMoudle extends BaseModel {
    private static final String TAG ="WebMoudle" ;

    public void getLikeData(String title,String author, String link, final ResultCallBack<String> callBack){
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        Observable<String> naviLike = apiserver.getNaviLike(title,author, link);
        naviLike.compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int errorCode = jsonObject.getInt("errorCode");
                            Log.e(TAG, "onNext: "+errorCode );
                            if (errorCode==WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess("收藏成功");

                            }else{
                                callBack.onFail(jsonObject.getString("errorMsg"));
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
