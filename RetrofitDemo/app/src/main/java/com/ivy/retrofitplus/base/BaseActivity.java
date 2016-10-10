package com.ivy.retrofitplus.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ivy.retrofitplus.utils.AppManager;

import butterknife.ButterKnife;


/**
 * Created by Ivy on 2016/10/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);// 添加Activity到堆栈
        initView();
        ButterKnife.bind(this);
        initListener();
        loadData();
    }

    protected abstract void loadData();


    /**
     * setContentView和findViewById操作
     */
    protected abstract void initView();

    /**
     * 给控件添加点击监听事件
     */
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    protected String getText(TextView tv) {
        return tv.getText().toString().trim();
    }
}
