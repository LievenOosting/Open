package com.qcj.common.base;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.qcj.common.cache.CacheManager;
import com.qcj.common.model.Entity;
import com.qcj.common.ui.EmptyLayout;
import com.qcj.common.widget.listview.BaseListView;

/**
 * author：qiuchunjia 用于有头部的，下面是列表。
 * <p/>
 * 比如一个发一个帖子，下面是评论
 */
public abstract class BaseHaveHeaderListFragment<T1 extends Entity, T2 extends Serializable>
        extends BaseListFragment<T1> {

    protected T2 detailBean;// list 头部的详情实体类
    protected Activity mActivity;
    private View mHeadView;

    protected final AsyncHttpResponseHandler mDetailHandler = new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            try {
                if (arg2 != null) {
                    T2 detail = getDetailBean(new ByteArrayInputStream(arg2));
                    if (detail != null) {
                        requstListData();
                        executeOnLoadDetailSuccess(detail);
                        new SaveCacheTask(getActivity(), detail,
                                getDetailCacheKey()).execute();
                    } else {
                        onFailure(arg0, arg1, arg2, null);
                    }
                } else {
                    throw new RuntimeException("load detail error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                onFailure(arg0, arg1, arg2, e);
            }
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                              Throwable arg3) {
            readDetailCacheData(getDetailCacheKey());
        }
    };

    @Override
    public void initView() {
        super.initView();
        mHeadView = initHeaderView();
        mListView.addHeaderView(mHeadView);
        requestDetailData();
    }


    protected abstract void requestDetailData();

    protected abstract View initHeaderView();

    protected abstract String getDetailCacheKey();

    /**
     * 绑定数据到headview上面
     *
     * @param detailBean
     */
    protected abstract void executeOnLoadDetailSuccess(T2 detailBean);

    protected abstract T2 getDetailBean(ByteArrayInputStream is);

    @Override
    protected boolean requestDataIfViewCreated() {
        return false;
    }

    private void requstListData() {
        mState = STATE_REFRESH;
        mListView.setState(BaseListView.STATE_LOAD_MORE);
        sendRequestData();
    }

    /***
     * 带有header view的listfragment不需要显示是否数据为空
     */
    @Override
    protected boolean needShowEmptyNoData() {
        return false;
    }

    protected void readDetailCacheData(String cacheKey) {
        new ReadCacheTask(getActivity()).execute(cacheKey);
    }

    private class SaveCacheTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<Context> mContext;
        private final Serializable seri;
        private final String key;

        private SaveCacheTask(Context context, Serializable seri, String key) {
            mContext = new WeakReference<Context>(context);
            this.seri = seri;
            this.key = key;
        }

        @Override
        protected Void doInBackground(Void... params) {
            CacheManager.saveObject(mContext.get(), seri, key);
            return null;
        }
    }

    private class ReadCacheTask extends AsyncTask<String, Void, T2> {
        private final WeakReference<Context> mContext;

        private ReadCacheTask(Context context) {
            mContext = new WeakReference<Context>(context);
        }

        @Override
        protected T2 doInBackground(String... params) {
            if (mContext.get() != null) {
                Serializable seri = CacheManager.readObject(mContext.get(),
                        params[0]);
                if (seri == null) {
                    return null;
                } else {
                    return (T2) seri;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(T2 t) {
            super.onPostExecute(t);
            if (t != null) {
                requstListData();
                executeOnLoadDetailSuccess(t);
            }
        }
    }

    @Override
    protected void executeOnLoadDataError(String error) {
        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        mListView.setState(BaseListView.STATE_NETWORK_ERROR);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 根据mheadview来获取指定的控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewByHead(int viewId) {
        return (T) mHeadView.findViewById(viewId);
    }
}
