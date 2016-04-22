package com.example.qiuchunjia.sample.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.qcj.common.http.ApiHttpClient;

/**
 * Created by qiuchunjia on 2016/4/22.
 */
public class ApiDataTest {
    //    http://www.chinaipo.com/api.php?app=api&mod=Information&act=hot_information&page=2
    public static void getData(int page, AsyncHttpResponseHandler handler) {
        ApiHttpClient.getAll("http://www.chinaipo.com/api.php?app=api&mod=Information&act=hot_information&page=" + page, handler);
    }
}
