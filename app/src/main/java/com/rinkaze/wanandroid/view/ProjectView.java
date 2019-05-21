package com.rinkaze.wanandroid.view;


import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.ProjectClassBean;

public interface ProjectView extends BaseMvpView {
    void setData(ProjectClassBean bean);
}
