package com.qcj.common.util;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class DefaultLayoutUtil {
	
	public static View showDefault(ViewGroup parent, View defaultView){
		int count = parent.getChildCount();
		for (int i = 0; i < count; i++) {
			parent.getChildAt(i).setVisibility(View.GONE);
		}
		parent.addView(defaultView);
		defaultView.setVisibility(View.VISIBLE);
		return defaultView;
	}
	public static View hideDefault(ViewGroup parent,View defaultView){
		int count = parent.getChildCount();
		for (int i = 0; i < count; i++) {
			parent.getChildAt(i).setVisibility(View.VISIBLE);
		}
		if(defaultView != null){
			defaultView.setVisibility(View.GONE);
			parent.removeView(defaultView);
		}
		return defaultView;
	}
}
