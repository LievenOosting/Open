package com.qcj.common.adapter.recycleview.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;


/**
 * 从底部滑动的动画
 */
public class SlideInBottomAnimation implements BaseAnimation {


    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
        };
    }
}
