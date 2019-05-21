package com.rinkaze.wanandroid.view;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;

public interface KnowledgeView extends BaseMvpView {
        void KnowledgeData(KnowledgeHierarchyData data);
        void ErrorData(String e);
}
