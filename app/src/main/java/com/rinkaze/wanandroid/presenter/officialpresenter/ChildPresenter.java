package com.rinkaze.wanandroid.presenter.officialpresenter;

import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.model.officialmoudle.ChildMoudle;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.officialview.ChildView;

public class ChildPresenter extends BasePresenter<ChildView> {
    ChildMoudle mChildMoudle;

    @Override
    protected void initModel() {
        mChildMoudle = new ChildMoudle();
        mModels.add(mChildMoudle);
    }

    public void  getArtData(int id,int page){
        mChildMoudle.getData(id, page, new ResultCallBack<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData bean) {
                if (bean!=null){
                    mMvpView.getSuccess(bean);
                }

            }

            @Override
            public void onFail(String msg) {
                mMvpView.getFailed(msg);
            }
        });

    }

    public void  getCollect(int id){
        mChildMoudle.getCollect(id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.getCollect(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.getCollectFailed(msg);
            }
        });
    }

    public void getDiscollect(int id,int originId,String name,String psw){
        mChildMoudle.getDisCollect(id, originId,name,psw, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.getDiscollect(bean);
            }

            @Override
            public void onFail(String msg) {
            mMvpView.getDiscollectFailed(msg);
            }
        });

    }
}
