package com.rinkaze.wanandroid.presenter;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.model.NaviLikeModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.navigation.NaviLikeView;

public class NaviLikePresenter extends BasePresenter<NaviLikeView> {
    NaviLikeModel model;


    @Override
    protected void initModel() {
            this.model=new NaviLikeModel();
            mModels.add(model);
    }
    public void initNaviLike(String name,String like){
        model.initNaviLike(name, like, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFina(msg);
            }
        });
    }
}
