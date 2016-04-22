package com.qcj.common.interf;

import com.qcj.common.model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 实体list，用于缓存时候
 *
 * @param <T>
 */
public interface ListEntity<T extends Entity> extends Serializable {

    public List<T> getList();
}
