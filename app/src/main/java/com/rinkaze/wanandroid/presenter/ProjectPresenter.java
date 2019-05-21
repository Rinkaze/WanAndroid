package com.rinkaze.wanandroid.presenter;


import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.ProjectClassBean;
import com.rinkaze.wanandroid.model.ProjectModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.ProjectView;

public class ProjectPresenter extends BasePresenter<ProjectView> {

    private ProjectModel mProjectModel;
    //初始化Model
    @Override
    protected void initModel() {
        mProjectModel = new ProjectModel();
        mModels.add(mProjectModel);
    }
    public void getData(){
       mProjectModel.getData(new ResultCallBack<ProjectClassBean>() {
           //加载成功
           @Override
           public void onSuccess(ProjectClassBean bean) {
               if (bean != null){
                   mMvpView.setData(bean);
               }
           }
           //加载失败
           @Override
           public void onFail(String msg) {
               ToastUtil.showLong(msg);
           }
       });
    }
}
