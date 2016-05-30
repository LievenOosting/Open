package com.example.qiuchunjia.sample;

import android.os.Handler;

import com.qcj.common.base.BaseActivity;
import com.qcj.common.model.Model;

/**
 * Created by qiuchunjia on 2016/5/11.
 */
public class BezierActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bezier;
    }

    @Override
    public void initView() {
        //测试bug收集专用
        Model model = null;
        model.equals("dd");
    }

    @Override
    public void initData() {

    }
}
