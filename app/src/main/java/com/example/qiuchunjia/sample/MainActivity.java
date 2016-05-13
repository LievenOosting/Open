package com.example.qiuchunjia.sample;

import com.example.qiuchunjia.sample.api.ApiDataTest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.qcj.common.base.BaseActivity;
import com.qcj.common.util.L;
import com.qcj.common.util.ToastUtils;
import com.qcj.common.widget.swipebacklayout.lib.app.SwipeBackActivity;

import org.apache.http.Header;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setToolBarTitle("我的");
        showWaitDialog("加载中...");
        ApiDataTest.getData(1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                L.d(TAG, "reponce=" + string);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showToast("获取失败");
            }
        });
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    public void initData() {

    }


}
