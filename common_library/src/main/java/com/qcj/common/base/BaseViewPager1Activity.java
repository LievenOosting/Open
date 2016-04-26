package com.qcj.common.base;


import android.support.v4.view.ViewPager;

import com.qcj.common.R;
import com.qcj.common.adapter.ViewPageFragmentAdapter;
import com.qcj.common.adapter.ViewPageFragmentAdapter1;
import com.qcj.common.ui.EmptyLayout;
import com.qcj.common.widget.PagerSlidingTabStrip;
import com.qcj.common.widget.ViewPagerIndicator;

/**
 * 带有导航条的基类
 *
 * @author qiuchunjia
 * @created 2016年2月6日
 */
public abstract class BaseViewPager1Activity extends BaseActivity {
    protected ViewPagerIndicator mViewPagerIndicator;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter1 mTabsAdapter;
    protected EmptyLayout mErrorLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.base_viewpage_fragment_and_activity;
    }

    @Override
    public void initView() {
        mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.viewpager_indicator);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mErrorLayout = (EmptyLayout) findViewById(R.id.error_layout);
        mTabsAdapter = new ViewPageFragmentAdapter1(getSupportFragmentManager(),
                mViewPagerIndicator, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
    }

    protected void setScreenPageLimit() {

    }


    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter1 adapter);
}