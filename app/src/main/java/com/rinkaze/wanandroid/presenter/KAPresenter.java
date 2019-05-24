package com.rinkaze.wanandroid.presenter;

import android.util.Log;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.model.KAModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.view.KAView;

public class KAPresenter extends BasePresenter<KAView> {
    private static final String TAG = "KAPresenter";
    KAModel model;
    @Override
    protected void initModel() {
        model=new KAModel();
        mModels.add(model);
    }
    public void initPresenter(int page,int cid){
        model.initKAModeData(page, cid, new ResultCallBack<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData bean) {
                mMvpView.KAData(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.ErrorData(msg);
            }
        });
    }
    public void initDeleteData(int id){
        model.initDeleteKAData(id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.KACancelData(bean);
            }

            @Override
            public void onFail(String msg) {
                Logger.logD(TAG,msg);
            }
        });
    }

    public void initCollectData(int id){
        model.initCollectData(id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.KACollectData(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.ErrorData(msg);
            }
        });
    }
}
