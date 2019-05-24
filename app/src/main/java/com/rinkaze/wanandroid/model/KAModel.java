package com.rinkaze.wanandroid.model;

import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.IListService;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import com.rinkaze.wanandroid.net.WanAndroidApi;
import com.rinkaze.wanandroid.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

public class KAModel extends BaseModel {

    private String psw;
    private String name;

    public void initKAModeData(int page, int cid, final ResultCallBack<FeedArticleListData> callBack) {
        psw = (String) SpUtil.getParam(Constants.PASSWORD, "");
        name = (String) SpUtil.getParam(Constants.USERNAME, "");
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getKnowledgeHierarchyDetailData(page, cid)
                .compose(RxUtils.<FeedArticleListData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>() {
                    @Override
                    public void onNext(FeedArticleListData data) {
                        callBack.onSuccess(data);
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

    public void initDeleteKAData(int id, final ResultCallBack<String> callBack) {
        WanAndroidApi apiserver = HttpUtils.getInstance().getApiserver(WanAndroidApi.baseUrl, WanAndroidApi.class);
        apiserver.getKADelete(name, psw, id)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        callBack.onSuccess("已取消");
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

    public void initCollectData(int id, final ResultCallBack<String> callBack) {
        IListService apiserver = HttpUtils.getInstance().getApiserver(IListService.BASE_URL, IListService.class);
        apiserver.getCollect(name, psw, id)
                .compose(RxUtils.<JSONObject>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject s) {
                        callBack.onSuccess("收藏成功");
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
