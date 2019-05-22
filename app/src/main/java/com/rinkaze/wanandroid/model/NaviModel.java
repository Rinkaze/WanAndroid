package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.icallback.ICallBack;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.servicelist.ServiceList;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class NaviModel extends BaseModel {
    public void initNaviTab(final ICallBack<Navi_Tab_Bean> iCallBack){
        ServiceList apiserver = HttpUtils.getInstance().getApiserver(ServiceList.url, ServiceList.class);
        Observable<Navi_Tab_Bean> naviTab = apiserver.getNaviTab();
        naviTab.compose(RxUtils.<Navi_Tab_Bean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Navi_Tab_Bean>() {
                    @Override
                    public void onNext(Navi_Tab_Bean navi_tab_bean) {
                        iCallBack.Success(navi_tab_bean);
                    }

                    @Override
                    public void error(String msg) {
                        iCallBack.Fain(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
