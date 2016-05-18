package com.example.qiuchunjia.sample;

import com.example.qiuchunjia.sample.adapter.MyRecycleAdapter;
import com.example.qiuchunjia.sample.api.ApiDataTest;
import com.example.qiuchunjia.sample.bean.DataModel;
import com.qcj.common.adapter.recycleview.BaseQuickAdapter;
import com.qcj.common.base.BaseRecycleViewActivity;
import com.qcj.common.util.JUtil;
import com.qcj.common.util.StreamTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qiuchunjia on 2016/4/22.
 */
public class ListRecycleActivity extends BaseRecycleViewActivity<DataModel> {
    private static final String TAG = "ListTestActivity";

    @Override
    public void initView() {
        super.initView();
        setToolbarTitle("recycleView的使用");
    }

    @Override
    protected BaseQuickAdapter getCommonAdapter() {
        return new MyRecycleAdapter(this, R.layout.item_test_data, null);
    }

    @Override
    protected void sendRequestData() {
        ApiDataTest.getData(mCurrentPage, mHandler);
    }

    @Override
    protected List parseList(InputStream is) throws Exception {
        String data = StreamTool.streamToString(is);
        JSONObject jsonObject = new JSONObject(data);
        final JSONObject data1 = jsonObject.getJSONObject("data");
        if (data1.has("news")) {
            final JSONArray news = data1.getJSONArray("news");
            List list = JUtil.handleResponseList(news.toString(), DataModel.class);
            return list;
        }


        return null;
    }

    @Override
    protected List readList(Serializable seri) {
        return (List) seri;
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "testListCache";
    }
}
