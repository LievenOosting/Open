package com.qcj.common.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 用于CommonUtils的工具集合类
 * 
 * @author qcj
 * 
 */
public class UIUtils {

	private static final String TAG = "UIUtils";

	/**
	 * 隐藏输入法
	 * 
	 * @param paramContext
	 * @param paramEditText
	 */
	public static void hideSoftKeyboard(Context paramContext,
			EditText paramEditText) {
		((InputMethodManager) paramContext
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
	}

	/**
	 * 显示输入法
	 * 
	 * @param paramContext
	 * @param paramEditText
	 */
	public static void showSoftKeyborad(Context paramContext,
			EditText paramEditText) {
		((InputMethodManager) paramContext
				.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(
				paramEditText, InputMethodManager.SHOW_FORCED);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getWindowWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */

	public static int getWindowHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

}
