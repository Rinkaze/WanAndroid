package com.rinkaze.wanandroid.presenter.officialpresenter;



import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.model.officialmoudle.WebMoudle;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.officialview.WebView;

public class WebPresenter extends BasePresenter<WebView> {
    WebMoudle webMoudle;

    @Override
    protected void initModel() {
        webMoudle = new WebMoudle();
        mModels.add(webMoudle);
    }

    public void initNaviLike(String title,String author,String link){
        webMoudle.getLikeData(title,author, link, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.getSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.getFailed(msg);
            }
        });
    }
}
