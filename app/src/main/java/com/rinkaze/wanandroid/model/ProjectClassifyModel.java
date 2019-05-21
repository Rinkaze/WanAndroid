package com.rinkaze.wanandroid.model;


import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.ProjectListBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.IListService;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ProjectClassifyModel extends BaseModel {
    //网络请求数据
    public void getData(final int page, final int cid, final ResultCallBack<ProjectListBean> callBack) {
        IListService apiserver = HttpUtils.getInstance().getApiserver(IListService.DataUrl, IListService.class);
        Observable<ProjectListBean> dataClassify = apiserver.getProjectListData(page, cid);
        dataClassify.compose(RxUtils.<ProjectListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectListBean>() {
                    @Override
                    public void onNext(ProjectListBean listBean) {
                        callBack.onSuccess(listBean);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
