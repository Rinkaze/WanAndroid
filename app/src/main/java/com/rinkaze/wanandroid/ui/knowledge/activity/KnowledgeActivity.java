package com.rinkaze.wanandroid.ui.knowledge.activity;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.FeedArticleListData;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.presenter.KAPresenter;
import com.rinkaze.wanandroid.presenter.KnowledgeP;
import com.rinkaze.wanandroid.ui.knowledge.adapter.KAFMAdapter;
import com.rinkaze.wanandroid.ui.knowledge.fragment.KAFragment;
import com.rinkaze.wanandroid.utils.Logger;
import com.rinkaze.wanandroid.view.EmptyView;
import com.rinkaze.wanandroid.view.KAView;
import com.rinkaze.wanandroid.view.KnowledgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KnowledgeActivity extends BaseActivity<KnowledgeView, KnowledgeP> implements KnowledgeView {

    private static final String TAG = "KnowledgeActivity";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tool_img)
    ImageView mToolimg;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tool_tv)
    TextView mToolTv;
    private ArrayList<Fragment> mListFM = new ArrayList<>();
    private ArrayList<String> mListSt = new ArrayList<>();
    private KAFMAdapter mKafmAdapter;
    private int postion;


    @Override
    protected KnowledgeP initPresenter() {
        return new KnowledgeP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge;
    }

    @Override
    public void KnowledgeData(KnowledgeHierarchyData data) {
        List<KnowledgeHierarchyData.DataBean.ChildrenBean> children = data.getData().get(postion).getChildren();
        for (int i = 0; i < children.size(); i++) {
            mListSt.add(children.get(i).getName());
            KAFragment kaFragment = new KAFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.DATA, children.get(i).getId());
            kaFragment.setArguments(bundle);
            mListFM.add(kaFragment);
        }
        mKafmAdapter = new KAFMAdapter(getSupportFragmentManager(), mListFM, mListSt);
        mVp.setAdapter(mKafmAdapter);
        mTab.setupWithViewPager(mVp);

    }

    @Override
    public void ErrorData(String e) {
        Logger.logD(TAG, e);
    }

    @Override
    protected void initView() {
        mPresenter.onKnowledgePresentrt();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(Constants.TITLE);
        postion = intent.getIntExtra(Constants.DESC, 1);

        mToolbar.setTitle("");
        mToolTv.setText(stringExtra);
        setSupportActionBar(mToolbar);

       mToolimg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
    }
    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"分享");
        return super.onCreateOptionsMenu(menu);
    }

    //监听选项菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:

                break;
        }
        return true;
    }

}
