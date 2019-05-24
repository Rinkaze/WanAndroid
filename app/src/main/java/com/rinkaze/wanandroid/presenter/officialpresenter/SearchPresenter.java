package com.rinkaze.wanandroid.presenter.officialpresenter;

import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.official.SearchBean;
import com.rinkaze.wanandroid.model.SearchModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.main.SearchView;

import java.util.HashMap;

public class SearchPresenter extends BasePresenter<SearchView>
        implements ResultCallBack<SearchBean> {

    private SearchModel searchModel;

    @Override
    protected void initModel() {
        searchModel = new SearchModel();
        mModels.add(searchModel);
    }
    public void getSearch(HashMap<String,Object>map){
        searchModel.getSearch(this,map);
    }

    @Override
    public void onSuccess(SearchBean bean) {
        mMvpView.onSuccess(bean);
    }

    @Override
    public void onFail(String msg) {
        mMvpView.onFail(msg);
    }
}
