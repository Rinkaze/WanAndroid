package com.rinkaze.wanandroid.model.officialmoudle;

import com.rinkaze.wanandroid.Bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidAPI;

import io.reactivex.disposables.Disposable;

public class ChildMoudle extends BaseModel {

    public void getData(int id, int page, final ResultCallBack<FeedArticleListData> callBack){
        WanAndroidAPI apiserver = HttpUtils.getInstance().getApiserver(WanAndroidAPI.BASE_URL, WanAndroidAPI.class);
        apiserver.getWxSumData(id,page)
                .compose(RxUtils.<FeedArticleListData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>() {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        callBack.onSuccess(feedArticleListData);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }
}
