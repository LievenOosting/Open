package com.qcj.common.interf;

import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * 指示器的抽象接口
 * Created by qiuchunjia on 2016/4/26.
 */
public interface Indicator {
    /**
     * 设置tabtitle
     *
     * @param datas
     */
    void setTabItemTitles(List<String> datas);

    /**
     * 添加viewpager和当前的位置
     *
     * @param viewPager
     * @param pos
     */
    void setViewPager(ViewPager viewPager, final int pos);
}
