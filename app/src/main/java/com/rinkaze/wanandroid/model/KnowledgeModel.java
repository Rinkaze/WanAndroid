package com.rinkaze.wanandroid.model;




import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;

import io.reactivex.disposables.Disposable;

public class KnowledgeModel extends BaseModel {
    private static final String TAG = "KnowledgeModel";

    public void onKnowledgeMode(final ResultCallBack<KnowledgeHierarchyData> callback){
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getKnowledgeHierarchyData()
                .compose(RxUtils.<KnowledgeHierarchyData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KnowledgeHierarchyData>() {
                    @Override
                    public void onNext(KnowledgeHierarchyData knowledgeHierarchyData) {
                        callback.onSuccess(knowledgeHierarchyData);
                    }

                    @Override
                    public void error(String msg) {
                        callback.onFail(msg);
                        Log.d(TAG, "error: "+msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        Log.d(TAG, "subscribe: "+d);
                        addDisposable(d);
                    }
                });
    }
}
