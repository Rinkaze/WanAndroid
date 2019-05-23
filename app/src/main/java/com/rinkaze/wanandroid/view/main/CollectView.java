package com.rinkaze.wanandroid.view.main;

import com.rinkaze.wanandroid.base.BaseMvpView;
import com.rinkaze.wanandroid.bean.MyCollectBean;

/**
 * Created by 灵风 on 2019/5/23.
 */

public interface CollectView extends BaseMvpView {
    void onSuccess(MyCollectBean.DataEntity collectBean);
    void disCollect(String msg);
    void onFail(String msg);
}
