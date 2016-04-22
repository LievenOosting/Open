package com.example.qiuchunjia.sample.adapter;

import android.content.Context;

import com.example.qiuchunjia.sample.R;
import com.example.qiuchunjia.sample.bean.DataModel;
import com.qcj.common.base.CommonAdapter;
import com.qcj.common.base.ViewHolder;

import java.util.ArrayList;


/**
 * Created by qiuchunjia on 2016/4/21.
 */
public class MyAdapter extends CommonAdapter<DataModel> {
    public MyAdapter(Context context, ArrayList<DataModel> datas, int layoutId) {
        super(context, datas, layoutId);
    }


    @Override
    public void convertView(ViewHolder holder, int pos) {
        DataModel dataModel = mDataList.get(pos);
        holder.setText(R.id.tv_title,dataModel.getTitle());
    }
}
