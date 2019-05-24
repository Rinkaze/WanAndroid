package com.rinkaze.wanandroid.model;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.CollectBean;
import com.rinkaze.wanandroid.bean.ProjectListBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.EveryWhereApi;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.IListService;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ProjectClassifyModel extends BaseModel {
    //网络请求数据    列表文章
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

    //点击关注
    //(boolean)Constants.LOGIN 登录状态
    //true：已登录
    //false：未登录

    private static final String TAG = "ProjectClassifyModel";
    public void getLike(int id, final ResultCallBack<String>resultCallBack){
        HttpUtils.getInstance().getApiserver(IListService.DataUrl,IListService.class)
                .getCollect(id)
                .compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        resultCallBack.onSuccess(s);
                    }

                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e="+msg );
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
    public void getDisLike(int disid, final ResultCallBack<String>resultCallBack){
        HttpUtils.getInstance().getApiserver(IListService.DataUrl,IListService.class)
                .getDisCollect(disid)
                .compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)){
                            CollectBean collectBean = new Gson().fromJson(s, CollectBean.class);
                            if (collectBean.getErrorCode() == WanAndroidApi.SUCCESS_CODE){
                                resultCallBack.onSuccess("");
                            }else {
                                resultCallBack.onFail(collectBean.getErrorMsg());
                            }
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }
}
