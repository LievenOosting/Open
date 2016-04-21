package com.qcj.common.interf;

import android.view.View;

/**
 * Created by qiuchunjia on 2015/4/19.
 * ui的初始化操作，baseactivity和basefragment都将实现它
 */
public interface UIInterface extends View.OnClickListener {
    void initView();

    void initData();

    void setListener();

    @Override
    void onClick(View v);
}
