package com.qcj.common.util;

import android.app.Activity;

import com.qcj.common.R;


/**
 * activity切换的动画工具类
 */
public class Anim {
    public static void exit(Activity obj) {
        obj.overridePendingTransition(R.anim.slide_in_from_left,
                R.anim.slide_out_from_right);
    }

    public static void in(Activity obj) {
        obj.overridePendingTransition(R.anim.slide_in_from_right,
                R.anim.slide_out_from_left);
    }

    /**
     * 从下往上退出当前，显示下一个 显示效果为下一个activity从底部推出当前activity
     */
    public static void startActivityFromBottom(Activity obj) {
        obj.overridePendingTransition(R.anim.slide_activity_upword_in,
                R.anim.slide_activity_upoword_out);
    }

    /**
     * 从上往下退出 显示效果为下一个activity从顶部推出当前activity
     *
     * @param obj
     */
    public static void startActivityFromTop(Activity obj) {
        obj.overridePendingTransition(R.anim.slide_activity_downword_in,
                R.anim.slide_activity_downword_out);
    }
}
