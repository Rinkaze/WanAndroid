package com.rinkaze.wanandroid.model;
import com.rinkaze.wanandroid.base.BaseModel;
import com.rinkaze.wanandroid.bean.official.SearchBean;
import com.rinkaze.wanandroid.net.BaseObserver;
import com.rinkaze.wanandroid.net.EveryWhereApi;
import com.rinkaze.wanandroid.net.HttpUtils;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.net.RxUtils;
import java.util.HashMap;
import io.reactivex.disposables.Disposable;

public class SearchModel extends BaseModel {
    public void getSearch(final ResultCallBack<SearchBean> resultCallBack, HashMap<String,Object>map){
        HttpUtils.getInstance().getApiserver(EveryWhereApi.baseUrl,EveryWhereApi.class)
                .getSearch(map)
                .compose(RxUtils.<SearchBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SearchBean>() {
                    @Override
                    public void onNext(SearchBean homeBean) {
                        if (homeBean!=null){
                            resultCallBack.onSuccess(homeBean);
                        }else {
                            resultCallBack.onFail("请求失败");
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
