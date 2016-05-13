package com.qcj.common.adapter.recycleview;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;


import com.qcj.common.adapter.recycleview.entity.MultiItemEntity;

import java.util.List;

public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity> extends BaseQuickAdapter {


    /**
     * 装多个布局界面的容器
     */
    private SparseArray<Integer> layouts;

    public BaseMultiItemQuickAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return ((MultiItemEntity) mData.get(position)).getItemType();
    }


    /**
     * 重写父类方法，实现布局文件的加载
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType);
    }

    /**
     * 添加布局文件保存起
     *
     * @param type
     * @param layoutResId
     */
    protected void addItemType(int type, int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(type, layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        convert(helper, (T) item);
    }

    protected abstract void convert(BaseViewHolder helper, T item);


}


