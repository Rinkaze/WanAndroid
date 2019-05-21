package com.rinkaze.wanandroid.presenter;


import com.rinkaze.wanandroid.base.BasePresenter;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;
import com.rinkaze.wanandroid.model.KnowledgeModel;
import com.rinkaze.wanandroid.net.ResultCallBack;
import com.rinkaze.wanandroid.view.KnowledgeView;

public class KnowledgeP extends BasePresenter<KnowledgeView> {
    KnowledgeModel knowledgeModel;
    @Override
    protected void initModel() {
        knowledgeModel=new KnowledgeModel();
        mModels.add(knowledgeModel);
    }
    public void onKnowledgePresentrt(){
        knowledgeModel.onKnowledgeMode(new ResultCallBack<KnowledgeHierarchyData>() {
            @Override
            public void onSuccess(KnowledgeHierarchyData bean) {
                mMvpView.KnowledgeData(bean);
            }

            @Override
            public void onFail(String msg) {
                 mMvpView.ErrorData(msg);
            }
        });
    }
}
