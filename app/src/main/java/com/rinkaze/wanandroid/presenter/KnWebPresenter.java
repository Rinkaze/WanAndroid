package com.rinkaze.wanandroid.presenter;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.model.KnWebModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.KnWebView;

public class KnWebPresenter extends BasePresenter<KnWebView> {
    KnWebModel knWebModel;
    @Override
    protected void initModel() {
        knWebModel=new KnWebModel();
        mModels.add(knWebModel);

    }
    public void initNaviLike(String title,String author,String link){
        knWebModel.onKnWebModel(title,author, link, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.KnWebData(bean);
            }
            @Override
            public void onFail(String msg) {
                mMvpView.ErrorData(msg);
            }
        });
    }
}
