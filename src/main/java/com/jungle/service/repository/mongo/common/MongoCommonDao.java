package com.jungle.service.repository.mongo.common;

import com.jungle.service.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jungle
 * @version Created on 2016/5/3.
 */
public abstract class MongoCommonDao {
    public abstract String getDefaultCollectionName();
    /**
     * mongo id批量转换
     *
     * @param metaDataList
     * @param isResponse
     */
    protected void convertId(List<Map<String, Object>> metaDataList, boolean isResponse) {
        if (null == metaDataList) {
            metaDataList = new ArrayList<>();
        }
        for (Map<String, Object> metaData : metaDataList) {
            convertId(metaData, isResponse);
        }
    }

    /**
     * 转换mongo id
     *
     * @param metaData
     * @param isResponse 是否返回前端，数据库默认_id, 前端使用id
     */
    protected void convertId(Map<String, Object> metaData, boolean isResponse) {
        if (null == metaData) {
            return;
        }
        if (isResponse) {
            if (metaData.containsKey("_id")) {
                String id = metaData.get("_id").toString();
                metaData.put("id", id);
                metaData.remove("_id");
            }
        } else {
            if (metaData.containsKey("id")) {
                String id = ObjectUtil.getStringOfObject(metaData.get("id"));
                metaData.put("_id", id);
                metaData.remove("id");
            }
        }
    }
}
