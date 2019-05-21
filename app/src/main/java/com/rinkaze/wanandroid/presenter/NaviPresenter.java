package com.rinkaze.wanandroid.presenter;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.icallback.ICallBack;
import com.rinkaze.wanandroid.model.NaviModel;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;
import com.rinkaze.wanandroid.view.NaviView;

public class NaviPresenter extends BasePresenter<NaviView> {
    NaviModel model;
    @Override
    protected void initModel() {
       this.model=new NaviModel();
       mModels.add(model);

    }
    public void initNavigation(){
        model.initNaviTab(new ICallBack<Navi_Tab_Bean>() {
            @Override
            public void Success(Navi_Tab_Bean bean) {
                mMvpView.initSuccess(bean);
            }

            @Override
            public void Fain(String msg) {
                mMvpView.initFani(msg);
            }
        });
    }
}

