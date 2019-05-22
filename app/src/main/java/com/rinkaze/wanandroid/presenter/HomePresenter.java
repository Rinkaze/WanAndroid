package com.rinkaze.wanandroid.presenter;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.model.HomeModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView>{

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        mModels.add(homeModel);
    }
    public void getHomeInit(int num){
        homeModel.getHomeInit(num, new ResultCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean bean) {
                mMvpView.onSuccess1(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail1(msg);
            }
        });
        homeModel.getBanner(new ResultCallBack<HomeBanner>() {
            @Override
            public void onSuccess(HomeBanner bean) {
                if (mMvpView != null){
                    mMvpView.onSuccess2(bean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null){
                    mMvpView.onFail2(msg);
                }
            }
        });
    }

}
