package com.rinkaze.wanandroid.presenter.officialpresenter;

import com.rinkaze.wanandroid.bean.official.WxAuthor;
import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.model.officialmoudle.OfficialMoudle;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.officialview.OfficialView;

public class OfficialPresenter extends BasePresenter<OfficialView> {
    OfficialMoudle mOfficialMoudle;
    @Override
    protected void initModel() {
        mOfficialMoudle = new OfficialMoudle();
        mModels.add(mOfficialMoudle);
    }

    public void  getAutherData(){
        mOfficialMoudle.getAutherData(new ResultCallBack<WxAuthor>() {
            @Override
            public void onSuccess(WxAuthor bean) {
                mMvpView.getSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.getFailed(msg);
            }
        });

    }
}
