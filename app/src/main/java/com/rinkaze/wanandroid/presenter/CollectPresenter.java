package com.rinkaze.wanandroid.presenter;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.MyCollectBean;
import com.rinkaze.wanandroid.model.CollectModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.main.CollectView;

/**
 * Created by 灵风 on 2019/5/23.
 */

public class CollectPresenter extends BasePresenter<CollectView> {

    private CollectModel model;

    @Override
    protected void initModel() {
        model = new CollectModel();
        mModels.add(model);
    }

    public void getCollectData(int page){
        model.getCollectData(page, new ResultCallBack<MyCollectBean>() {
            @Override
            public void onSuccess(MyCollectBean bean) {
                mMvpView.onSuccess(bean.getData());
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }

    public void disCollect(int id, int originId) {
        model.disCollect(id,originId, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.disCollect(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
