package com.rinkaze.wanandroid.presenter;


import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.ProjectListBean;
import com.rinkaze.wanandroid.model.ProjectClassifyModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.rinkaze.wanandroid.view.ProjectClassifyView;

public class ProjectClassPresenter extends BasePresenter<ProjectClassifyView> {
    private boolean isselect=true;
    private boolean isselectfain=false;
    private ProjectClassifyModel mProjectClassifyModel;
//初始化Modle
    @Override
    protected void initModel() {
        mProjectClassifyModel = new ProjectClassifyModel();
        mModels.add(mProjectClassifyModel);
    }
    public void getData(int page,int cid){
        mProjectClassifyModel.getData(page, cid, new ResultCallBack<ProjectListBean>() {
            //加载成功
            @Override
            public void onSuccess(ProjectListBean bean) {
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
    public void Like(int id){
        mProjectClassifyModel.getLike(id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.onCollectSucceed(bean);
                SpUtil.setParam("isselect",isselect);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onCollectFalse(msg);
                SpUtil.setParam("isselectfain",isselectfain);
        }
        });
    }
    public void Dislike(int disid){
        mProjectClassifyModel.getDisLike(disid, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.onCollectSucceed(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onCollectFalse(msg);
            }
        });
    }


}
