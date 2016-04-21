package com.example.qiuchunjia.sample.adapter;

import android.content.Context;

import com.qcj.common.base.CommonAdapter;
import com.qcj.common.base.ViewHolder;

import java.util.List;

/**
 * Created by qiuchunjia on 2016/4/21.
 */
public class MyAdapter<Model> extends CommonAdapter {
    public MyAdapter(Context context, List<Model> datas, int layoutId) {
        super(context, datas, layoutId);
    }


    @Override
    public void convertView(ViewHolder holder, int pos) {

    }
}
