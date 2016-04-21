package com.qcj.common.interf;


import com.qcj.common.base.PopView;

/**
 * author：qiuchunjia time：下午2:42:52 类描述：这个类是实现
 *
 */

public interface PopInterface {
	public int getLayoutId();

	/**
	 * 初始化布局
	 */
	public void initPopView();

	/**
	 * 初始化数据
	 * 
	 * @param object
	 */
	public void initPopData(Object object);

	/**
	 * 设置监听器
	 * 
	 * @return
	 */
	public void setPopLisenter(PopView.PopResultListener listener);
}
