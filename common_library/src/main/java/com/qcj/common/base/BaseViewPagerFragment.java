package com.qcj.common.base;


import android.support.v4.view.ViewPager;

import com.qcj.common.R;
import com.qcj.common.adapter.ViewPageFragmentAdapter;
import com.qcj.common.ui.EmptyLayout;
import com.qcj.common.widget.PagerSlidingTabStrip;

/**
 * 带有导航条的基类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年11月6日 下午4:59:50
 */
public abstract class BaseViewPagerFragment extends BaseFragment {

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
        mTabStrip = findViewById(R.id.pager_tabstrip);
        mViewPager = findViewById(R.id.pager);
        mErrorLayout = findViewById(R.id.error_layout);
        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
    }

    protected void setScreenPageLimit() {

    }


    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
}