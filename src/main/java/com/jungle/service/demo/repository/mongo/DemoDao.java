package com.jungle.service.demo.repository.mongo;

import com.jungle.service.commons.MongoCommonDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jungle
 * @version Created on 2016/5/3.
 */
@Repository
public class DemoDao extends MongoCommonDao {
    private static final String COLLECTION_NAME = "test";

    @Resource
    private MongoTemplate mongoTemplate;

    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map> rawList = mongoTemplate.findAll(Map.class,  "test");
        if(!CollectionUtils.isEmpty(rawList)) {
            for(Map map : rawList) {
                list.add((Map<String, Object>) map);
            }
        }
        convertId(list, true);
        return list;
    }

    @Override
    public String getDefaultCollectionName() {
        return COLLECTION_NAME;
    }
}
