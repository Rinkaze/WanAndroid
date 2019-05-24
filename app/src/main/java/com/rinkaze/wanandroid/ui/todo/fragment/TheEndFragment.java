package com.rinkaze.wanandroid.ui.todo.fragment;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseFragment;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.view.EmptyView;

public class TheEndFragment extends BaseFragment<EmptyView,EmptyPresenter> {
    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theend;
    }
}
