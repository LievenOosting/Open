package com.qcj.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.qcj.common.interf.MultiItemTypeSupport;

import java.util.ArrayList;

/*
  autor： qiuchunjia
* 当listview 存在不同的item种类的item的时候就继承这个adapter
*
* */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T>
{

	protected MultiItemTypeSupport mMultiItemTypeSupport;

	public MultiItemCommonAdapter(Context context, ArrayList<T> datas,
			MultiItemTypeSupport  multiItemTypeSupport)
	{
		super(context, datas, -1);
		mMultiItemTypeSupport = multiItemTypeSupport;
	}

	@Override
	public int getViewTypeCount()
	{
		if (mMultiItemTypeSupport != null)
			return mMultiItemTypeSupport.getViewTypeCount();
		return super.getViewTypeCount();
	}

	@Override
	public int getItemViewType(int position)
	{
		if (mMultiItemTypeSupport != null)
			if (mDataList==null){
				return mMultiItemTypeSupport.getItemViewType(position,
						null);
			}
			return mMultiItemTypeSupport.getItemViewType(position,
					mDataList.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (mMultiItemTypeSupport == null)
			return super.getView(position, convertView, parent);

		int layoutId = mMultiItemTypeSupport.getLayoutId(position,
				getItem(position));
		ViewHolder viewHolder = ViewHolder.get(mBaseActivity, convertView, parent,
				layoutId, position);
		convertView(viewHolder, position);
		return viewHolder.getConvertView();
	}

}
