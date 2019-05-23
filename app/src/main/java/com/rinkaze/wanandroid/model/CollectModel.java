package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.MyCollectBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.Logger;

import io.reactivex.disposables.Disposable;

/**
 * Created by 灵风 on 2019/5/23.
 */

public class CollectModel extends BaseModel {
    private static final String TAG = "CollectModel";

    public void getCollectData(int page, final ResultCallBack<MyCollectBean> callBack){
        HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl,WanAndroidApi.class)
                .getCollectData(page)
                .compose(RxUtils.<MyCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyCollectBean>() {
                    @Override
                    public void error(String msg) {
                        Logger.logD(TAG,msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MyCollectBean myCollectBean) {
                        if (myCollectBean != null){
                            if (myCollectBean.getErrorCode() == WanAndroidApi.SUCCESS_CODE){
                                callBack.onSuccess(myCollectBean);
                            }else {
                                callBack.onFail(myCollectBean.getErrorMsg());
                            }
                        }
                    }
                });
    }
}
