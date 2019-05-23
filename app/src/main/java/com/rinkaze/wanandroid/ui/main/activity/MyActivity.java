package com.rinkaze.wanandroid.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.MyPresenter;
import com.rinkaze.wanandroid.ui.official.activity.WebViewActivity;
import com.rinkaze.wanandroid.view.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends BaseActivity<MyView, MyPresenter> implements MyView {


    @BindView(R.id.text6)
    TextView mText6;

    @Override
    protected MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView() {
        super.initView();

    }


}
