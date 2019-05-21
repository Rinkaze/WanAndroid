package com.rinkaze.wanandroid.presenter.officialpresenter;

import com.rinkaze.wanandroid.Bean.official.FeedArticleListData;
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
}
