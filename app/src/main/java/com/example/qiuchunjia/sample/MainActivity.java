package com.example.qiuchunjia.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qcj.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
            setToolBarTitle("我的");
        showWaitDialog("加载中...");
    }

    @Override
    public void initData() {

    }

}
