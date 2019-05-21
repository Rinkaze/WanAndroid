package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.FeedArticleListData;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidAPi;

import io.reactivex.disposables.Disposable;

public class KAModel extends BaseModel {

    public void initKAModeData(int page, int cid, final ResultCallBack<FeedArticleListData> callBack){
        WanAndroidAPi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidAPi.url, WanAndroidAPi.class);
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
}
