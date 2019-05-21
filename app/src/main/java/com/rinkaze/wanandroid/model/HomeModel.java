package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.EveryWhereApi;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {
    public void getHomeInit(int num, final ResultCallBack<HomeBean> resultCallBack){
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getHomeInit(num)
                .compose(RxUtils.<HomeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean!=null){
                            resultCallBack.onSuccess(homeBean);
                        }else {
                            resultCallBack.onFail("请求失败");
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
    public void getBanner(final ResultCallBack<HomeBanner>resultCallBack){
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getBanninit()
                .compose(RxUtils.<HomeBanner>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBanner>() {
                    @Override
                    public void onNext(HomeBanner homeBanner) {
                        if (homeBanner!=null){
                            resultCallBack.onSuccess(homeBanner);
                        }else {
                            resultCallBack.onFail("请求失败");
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }
}
