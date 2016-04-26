package com.qcj.common.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qcj.common.R;
import com.qcj.common.model.ViewPageInfo;
import com.qcj.common.widget.PagerSlidingTabStrip;
import com.qcj.common.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Recycle")
public class ViewPageFragmentAdapter1 extends FragmentStatePagerAdapter {

    private final Context mContext;
    protected ViewPagerIndicator mIndicator;
    private final ViewPager mViewPager;
    private final ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();

    public ViewPageFragmentAdapter1(FragmentManager fm,
                                    ViewPagerIndicator indicator, ViewPager pager) {
        super(fm);
        mContext = pager.getContext();
        mIndicator = indicator;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mIndicator.setViewPager(mViewPager, 0);
    }


    /**
     * 添加数据到viewpager到指示器
     *
     * @param pageInfos
     */
    public void addDataToIndicator(List<ViewPageInfo> pageInfos) {
        if (pageInfos != null && pageInfos.size() > 0) {
            mTabs.clear();
            mTabs.addAll(pageInfos);
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < pageInfos.size(); i++) {
                ViewPageInfo info = pageInfos.get(i);
                stringList.add(info.title);
            }
            mIndicator.setTabItemTitles(stringList);
            notifyDataSetChanged();

        }
    }


    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }
}