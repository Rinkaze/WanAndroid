package com.rinkaze.wanandroid.ui.todo.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.BaseActivity;
import com.rinkaze.wanandroid.presenter.EmptyPresenter;
import com.rinkaze.wanandroid.ui.todo.fragment.TheEndFragment;
import com.rinkaze.wanandroid.ui.todo.fragment.ToDoFragment;
import com.rinkaze.wanandroid.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ToDoActivity extends BaseActivity<EmptyView, EmptyPresenter> {

    @BindView(R.id.todo_frame)
    FrameLayout todoFrame;
    @BindView(R.id.todo_tablayoutt)
    TabLayout todoTablayoutt;
    @BindView(R.id.todo_back)
    ImageView todoBack;
    @BindView(R.id.todo_qiehuan)
    ImageView todoQiehuan;
    @BindView(R.id.todo_toolbar)
    Toolbar todoToolbar;
    @BindView(R.id.todo_btn)
    FloatingActionButton todoBtn;
    private FragmentManager manager;
    private int TODO=0;
    private int THEEND=1;
    private int lastposition=0;
    private ArrayList<Fragment> fraglist;
    private ToDoFragment toDoFragment;
    private TheEndFragment theEndFragment;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to_do;
    }

    @Override
    protected void initView() {
        super.initView();
        todoTablayoutt.addTab(todoTablayoutt.newTab().setText("代办").setIcon(R.drawable.daiban));
        todoTablayoutt.addTab(todoTablayoutt.newTab().setText("已完成").setIcon(R.drawable.wancheng));


        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        toDoFragment = new ToDoFragment();
        theEndFragment = new TheEndFragment();
        fraglist = new ArrayList<>();
        fraglist.add(toDoFragment);
        fraglist.add(theEndFragment);
        transaction.add(R.id.todo_frame,fraglist.get(0));
        transaction.commit();
        //fragmeng 的隐藏显示
        todoTablayoutt.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragmentswitch(TODO);
                        break;
                    case 1:
                        fragmentswitch(THEEND);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    private void fragmentswitch(int type) {
        Fragment fragment = fraglist.get(type);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.todo_frame,fragment);
        }

        transaction.hide(fraglist.get(lastposition));
        transaction.show(fragment);
        transaction.commit();
        lastposition=type;
    }


    @OnClick({R.id.todo_back, R.id.todo_qiehuan, R.id.todo_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.todo_back:
                finish();
                break;
            case R.id.todo_qiehuan:

                break;
            case R.id.todo_btn:
                Intent intent = new Intent(ToDoActivity.this, EditActivity.class);
                startActivity(intent);
                break;
        }
    }
}
