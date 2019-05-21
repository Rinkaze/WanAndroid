package com.rinkaze.wanandroid.model.officialmoudle;

import com.rinkaze.wanandroid.Bean.official.WxAuthor;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidAPI;

import io.reactivex.disposables.Disposable;

public class OfficialMoudle extends BaseModel {
    public  void getAutherData(final ResultCallBack<WxAuthor> callBack){
        WanAndroidAPI apiserver = HttpUtils.getInstance().getApiserver(WanAndroidAPI.BASE_URL, WanAndroidAPI.class);
        apiserver.getWxAuthorListData()
                .compose(RxUtils.<WxAuthor>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxAuthor>() {
                    @Override
                    public void onNext(WxAuthor wxAuthor) {
                        callBack.onSuccess(wxAuthor);
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
