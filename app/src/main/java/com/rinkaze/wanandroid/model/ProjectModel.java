package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.ProjectClassBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.IListService;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class ProjectModel extends BaseModel {
    //网络请求数据
    public void getData(final ResultCallBack<ProjectClassBean> callBack){
        IListService apiserver = HttpUtils.getInstance().getApiserver(IListService.BASE_URL, IListService.class);
        Observable<ProjectClassBean> dataClassify = apiserver.getProjectClassifyData();
        dataClassify.compose(RxUtils.<ProjectClassBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectClassBean>() {
                    @Override
                    public void onNext(ProjectClassBean classifyBean) {
                        callBack.onSuccess(classifyBean);
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
