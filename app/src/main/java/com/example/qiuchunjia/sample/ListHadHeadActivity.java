package com.example.qiuchunjia.sample;

import android.view.View;
import android.widget.TextView;

import com.example.qiuchunjia.sample.adapter.MyAdapter;
import com.example.qiuchunjia.sample.api.ApiDataTest;
import com.example.qiuchunjia.sample.bean.DataHeadModel;
import com.example.qiuchunjia.sample.bean.DataModel;
import com.qcj.common.base.AppContext;
import com.qcj.common.base.BaseHaveHeaderListActivity;
import com.qcj.common.adapter.CommonAdapter;
import com.qcj.common.util.JUtil;
import com.qcj.common.util.StreamTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by qiuchunjia on 2016/4/23.
 */
public class ListHadHeadActivity extends BaseHaveHeaderListActivity<DataModel, DataHeadModel> {


    @Override
    protected void requestDetailData() {
        ApiDataTest.getData(0, mDetailHandler);

    }

    @Override
    protected View initHeaderView() {
        return mInflater.inflate(R.layout.item_test_head, null);
    }

    @Override
    protected String getDetailCacheKey() {
        return "testHead";
    }


    @Override
    protected void executeOnLoadDetailSuccess(DataHeadModel detailBean) {
        setToolbarTitle("有头部的列表");
        TextView textView = findViewByHead(R.id.tv_head);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppContext.getInstance().startActivity(ListHadHeadActivity.this, ViewPagerTestActivity.class, null);
            }
        });
        textView.setText(detailBean.getData());
    }

    @Override
    protected DataHeadModel getDetailBean(ByteArrayInputStream is) {
        try {
            String content = StreamTool.streamToString(is);
            try {
                return JUtil.handleResponseObject(content, DataHeadModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void sendRequestData() {
        ApiDataTest.getData(mCurrentPage, mHandler);
    }

    @Override
    protected List parseList(InputStream is) throws Exception {
        String data = StreamTool.streamToString(is);
        org.json.JSONObject jsonObject = new org.json.JSONObject(data);
        final JSONObject data1 = jsonObject.getJSONObject("data");
        if (data1.has("news")) {
            final JSONArray news = data1.getJSONArray("news");
            List list = JUtil.handleResponseList(news.toString(), DataModel.class);
            return list;
        }


        return null;
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "fsdfasdf";
    }

    @Override
    protected CommonAdapter<DataModel> getCommonAdapter() {
        return new MyAdapter(this, null, R.layout.item_test_data);
    }
}
