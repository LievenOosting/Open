package com.example.qiuchunjia.sample.adapter;

import android.content.Context;
import android.view.View;

import com.example.qiuchunjia.sample.BezierActivity;
import com.example.qiuchunjia.sample.ListHadHeadActivity;
import com.example.qiuchunjia.sample.ListRecycleActivity;
import com.example.qiuchunjia.sample.R;
import com.example.qiuchunjia.sample.ViewPager1TestActivity;
import com.example.qiuchunjia.sample.ViewPager2TestActivity;
import com.example.qiuchunjia.sample.bean.DataModel;
import com.qcj.common.adapter.CommonAdapter;
import com.qcj.common.adapter.ViewHolder;
import com.qcj.common.base.AppContext;
import com.qcj.common.util.ToastUtils;
import com.qcj.common.widget.RippleView;

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
        RippleView rippleView = holder.getView(R.id.rv_view);
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                                                   @Override
                                                   public void onComplete(RippleView rippleView) {
                                                       if (pos == 1) {
                                                           AppContext.getInstance().startActivity(mBaseActivity, ViewPager1TestActivity.class, null);
                                                       } else if (pos == 2) {
                                                           AppContext.getInstance().startActivity(mBaseActivity, ViewPager2TestActivity.class, null);
                                                       } else if (pos == 3) {
                                                           AppContext.getInstance().startActivity(mBaseActivity, BezierActivity.class, null);
                                                       } else if (pos == 4) {
                                                           AppContext.getInstance().startActivity(mBaseActivity, ListRecycleActivity.class, null);
                                                       } else {
                                                           AppContext.getInstance().startActivity(mBaseActivity, ListHadHeadActivity.class, null);
                                                       }
                                                   }

                                               }

        );
    }
}
