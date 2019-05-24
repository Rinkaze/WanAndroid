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
import com.rinkaze.wanandroid.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ProjectClassifyModel extends BaseModel {

    private String psw;
    private String name;

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

    public void getLike(int id, final ResultCallBack<String> resultCallBack) {
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        HttpUtils.getInstance().getApiserver(IListService.DataUrl, IListService.class)
                .getCollect(name, psw, id)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        resultCallBack.onSuccess(s.toString());
                    }

                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e=" + msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

    public void getDisLike(int disid, final ResultCallBack<String> resultCallBack) {
        HttpUtils.getInstance().getApiserver(IListService.DataUrl, IListService.class)
                .getDisCollect(name, psw, disid)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        if (s != null) {
                            resultCallBack.onSuccess("");
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
