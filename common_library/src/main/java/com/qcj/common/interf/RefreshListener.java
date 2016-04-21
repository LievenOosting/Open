package com.qcj.common.interf;

/**
 * Created by qiuchunjia on 2016/3/2.
 * 这个接口用到的listview下拉刷新，上拉加载更多
 */
public interface RefreshListener {
    /*
    * adapter有数据的时候，然后下来就会刷新头部
    *
    * */
    public void doRefreshHead();

    /*
    *上拉加载更多的时候需要用到
    *
    * */
    public void doRefreshFooter();
}
