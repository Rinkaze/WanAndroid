package com.rinkaze.wanandroid.view;


import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.ProjectListBean;

public interface ProjectClassifyView extends BaseMvpView {

    void setData(ProjectListBean bean);
    void onCollectSucceed(String bean);
    void onCollectFalse(String msg2);
}
