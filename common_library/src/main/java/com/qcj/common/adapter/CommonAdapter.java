package com.qcj.common.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qcj.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private static final String TAG = "CommonAdapter";
    public LayoutInflater mInflater;
    private int layoutId;  //布局id
    public ArrayList<T> mDataList;
    public BaseActivity mBaseActivity;

    public CommonAdapter(Context context, ArrayList<T> datas, int layoutId) {
        this.mDataList = datas;
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
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

    public void setData(ArrayList<T> data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return mDataList == null ? (mDataList = new ArrayList<T>()) : mDataList;
    }

    public void addData(List<T> data) {
        if (mDataList != null && data != null && !data.isEmpty()) {
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addItem(T obj) {
        if (mDataList != null) {
            mDataList.add(obj);
        }
        notifyDataSetChanged();
    }

    public void addItem(int pos, T obj) {
        if (mDataList != null) {
            mDataList.add(pos, obj);
        }
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        mDataList.remove(obj);
        notifyDataSetChanged();
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }
}
