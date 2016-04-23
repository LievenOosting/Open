package com.qcj.common.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

import com.qcj.common.interf.RefreshListener;
import com.qcj.common.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by qiuchunjia on 2016/3/2.
 */
public class BaseListView extends XListView implements XListView.IXListViewListener {
    /******************
     * BaseListView的几种状态
     *********************/
    public static final int STATE_EMPTY_ITEM = 0;  //adapter中没有数据，隐藏footer
    public static final int STATE_LOAD_MORE = 1;  // 加载更多 footer显示加载更多
    public static final int STATE_NO_MORE = 2;//  没有更多数据了，比如规定数据规定返回20条，结果只返回了15条，这个时候只需要toast一条提示信息就ok
    public static final int STATE_LESS_ONE_PAGE = 3;// 少于一页，这个时候隐藏footer
    public static final int STATE_NETWORK_ERROR = 4;// 网络错误
    public static final int STATE_OTHER = 5;    //  其它未知的错误

    protected int mCurrentstate = STATE_LESS_ONE_PAGE;
    private RefreshListener mRefreshListener;  //下拉刷新，上拉加载更多的监听器

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initXListView();
    }

    public BaseListView(Context context) {
        super(context);
        initXListView();
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initXListView();
    }

    /*
    * 初始化xlistview的设置
    * */
    private void initXListView() {
        this.setPullRefreshEnable(true);
        this.setPullLoadEnable(true);
        this.setRefreshTime(getTime());
        this.setXListViewListener(this);
    }

    @Override
    public void onRefresh() {
        if (mRefreshListener == null) {
            return;
        }
        mRefreshListener.doRefreshHead();  //当数据不为零的时候更新数据到头部
    }

    @Override
    public void onLoadMore() {
        if (mRefreshListener == null) {
            return;
        }
        mRefreshListener.doRefreshFooter();
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * 取消下拉刷新 或者加载更多
     */
    public void onStopLoad() {
        this.stopRefresh();
        this.stopLoadMore();
        this.setRefreshTime(getTime());
    }

    /*************************
     * get和set方法生成
     ******************************************************/
    public RefreshListener getRefreshListener() {
        return mRefreshListener;
    }

    public void setRefreshListener(RefreshListener mRefreshListener) {
        this.mRefreshListener = mRefreshListener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        if (totalItemCount == visibleItemCount - firstVisibleItem) {
            setFooterViewGone();   //当只有一屏幕的时候隐藏
        }else{
            setFooterVisable();
        }
    }

    /**
     * 设置当前状态
     *
     * @param state
     */
    public void setState(int state) {
        switch (state) {
            case STATE_EMPTY_ITEM:
                setFooterVisable();
                break;
            case STATE_LOAD_MORE:
                //Todo  设置footer加载更多
                setFooterVisable();
                break;
            case STATE_NO_MORE:
                ToastUtils.showToast("没有更多了");
                break;
            case STATE_LESS_ONE_PAGE:
                //Todo  设置footer 隐藏footer
                setFooterViewGone();
                break;
            case STATE_NETWORK_ERROR:
                ToastUtils.showToast("网络错误");
                break;
            case STATE_OTHER:
                ToastUtils.showToast("未知错误");
                break;

        }
        this.mCurrentstate = state;
    }

    public int getCurrentstate() {
        return mCurrentstate;
    }
}
