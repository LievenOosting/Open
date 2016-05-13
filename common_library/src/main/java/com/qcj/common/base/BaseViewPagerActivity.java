package com.qcj.common.base;


import android.support.v4.view.ViewPager;

import com.qcj.common.R;
import com.qcj.common.adapter.ViewPageFragmentAdapter;
import com.qcj.common.ui.EmptyLayout;
import com.qcj.common.widget.PagerSlidingTabStrip;


public abstract class BaseViewPagerActivity extends BaseActivity {
    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mTabsAdapter;
    protected EmptyLayout mErrorLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.base_viewpage_fragment;
    }

    @Override
    public void initView() {
        mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_tabstrip);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mErrorLayout = (EmptyLayout) findViewById(R.id.error_layout);
        mTabsAdapter = new ViewPageFragmentAdapter(getSupportFragmentManager(),
                mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
    }

    protected void setScreenPageLimit() {

    }


    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
}