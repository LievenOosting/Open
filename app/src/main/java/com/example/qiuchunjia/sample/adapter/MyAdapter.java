package com.example.qiuchunjia.sample.adapter;

import android.content.Context;
import android.view.View;

import com.example.qiuchunjia.sample.ListHadHeadActivity;
import com.example.qiuchunjia.sample.R;
import com.example.qiuchunjia.sample.ViewPager1TestActivity;
import com.example.qiuchunjia.sample.ViewPager2TestActivity;
import com.example.qiuchunjia.sample.bean.DataModel;
import com.qcj.common.adapter.CommonAdapter;
import com.qcj.common.adapter.ViewHolder;
import com.qcj.common.base.AppContext;
import com.qcj.common.util.ToastUtils;

import java.util.ArrayList;


/**
 * Created by qiuchunjia on 2016/4/21.
 */
public class MyAdapter extends CommonAdapter<DataModel> {
    public MyAdapter(Context context, ArrayList<DataModel> datas, int layoutId) {
        super(context, datas, layoutId);
    }


    @Override
    public void convertView(ViewHolder holder, final int pos) {
        DataModel dataModel = mDataList.get(pos);
        holder.setText(R.id.tv_title, dataModel.getTitle());
        holder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast("position=" + pos);
                AppContext.getInstance().startActivity(mBaseActivity, ListHadHeadActivity.class, null);
            }
        });
        if (pos == 1) {
            holder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showToast("position=" + pos);
                    AppContext.getInstance().startActivity(mBaseActivity, ViewPager1TestActivity.class, null);
                }
            });
        }
        if (pos == 2) {
            holder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showToast("position=" + pos);
                    AppContext.getInstance().startActivity(mBaseActivity, ViewPager2TestActivity.class, null);
                }
            });
        }
    }
}
