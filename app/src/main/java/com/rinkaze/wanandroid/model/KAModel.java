package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

public class KAModel extends BaseModel {

    public void initKAModeData(int page, int cid, final ResultCallBack<FeedArticleListData> callBack){
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getKnowledgeHierarchyDetailData(page,cid)
                .compose(RxUtils.<FeedArticleListData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>() {
                    @Override
                    public void onNext(FeedArticleListData data) {
                        callBack.onSuccess(data);
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
    public void initDeleteKAData(int id,final ResultCallBack<String> callBack){
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getKADelete(id)
                .compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            int code = jsonObject.getInt("errorCode");
                            if (code==WanAndroidApi.SUCCESS_CODE){
                               callBack.onSuccess("已取消");
                            }else {
                                callBack.onSuccess("取消失败");
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
