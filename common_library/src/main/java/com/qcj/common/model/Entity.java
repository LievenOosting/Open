package com.qcj.common.model;

import java.util.List;

/**
 * Created by qiuchunjia on 2016/4/22.
 */
public class Entity extends Model {
    private List data;
    protected int id;  //实体的id
    protected String cacheKey;  //实体的缓存路径

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

}