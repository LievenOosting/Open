package com.example.qiuchunjia.sample;

import android.os.Bundle;

import com.example.qiuchunjia.sample.fragment.TestFragment;
import com.qcj.common.adapter.ViewPageFragmentAdapter1;
import com.qcj.common.base.BaseViewPager1Activity;
import com.qcj.common.model.ViewPageInfo;

import java.util.ArrayList;

/**
 * Created by qiuchunjia on 2016/4/26.
 */
public class ViewPager2TestActivity extends BaseViewPager1Activity {
    @Override
    protected int getLayoutId() {
        return R.layout.base_viewpage_fragment_and_activity1;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter1 adapter) {
        ArrayList<ViewPageInfo> viewPageInfos = new ArrayList<>();
        ViewPageInfo viewPageInfo1 = new ViewPageInfo("你好", "hh", TestFragment.class, new Bundle());
        ViewPageInfo viewPageInfo2 = new ViewPageInfo("我好", "hh", TestFragment.class, new Bundle());
        ViewPageInfo viewPageInfo3 = new ViewPageInfo("大家好", "hh", TestFragment.class, new Bundle());
        ViewPageInfo viewPageInfo4 = new ViewPageInfo("何洪达", "hh", TestFragment.class, new Bundle());
        ViewPageInfo viewPageInfo5 = new ViewPageInfo("雅蠛蝶", "hh", TestFragment.class, new Bundle());
        ViewPageInfo viewPageInfo6 = new ViewPageInfo("爱", "hh", TestFragment.class, new Bundle());
        ViewPageInfo viewPageInfo7 = new ViewPageInfo("666+", "hh", TestFragment.class, new Bundle());
        viewPageInfos.add(viewPageInfo1);
        viewPageInfos.add(viewPageInfo2);
        viewPageInfos.add(viewPageInfo3);
        viewPageInfos.add(viewPageInfo4);
        viewPageInfos.add(viewPageInfo5);
        viewPageInfos.add(viewPageInfo6);
        viewPageInfos.add(viewPageInfo7);
        adapter.addDataToIndicator(viewPageInfos);
    }

    @Override
    public void initData() {

    }

}
