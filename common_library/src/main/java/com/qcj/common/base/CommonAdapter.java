package com.qcj.common.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private static final String TAG = "CommonAdapter";
    public LayoutInflater mInflater;
    private int layoutId;  //布局id
    public List<T> mDataList;
    public BaseActivity mBaseActivity;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mDataList = datas;
        this.layoutId = layoutId;
        if (context instanceof BaseActivity) {
            mInflater = LayoutInflater.from(context);
            mBaseActivity = (BaseActivity) context;
        } else {
            Log.d(TAG, "activity 必须继承baseActivity");
        }
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mBaseActivity, convertView, parent,
                layoutId, position);
        convertView(holder, position);
        return holder.getConvertView();
    }

    public abstract void convertView(ViewHolder holder, int pos);

}
