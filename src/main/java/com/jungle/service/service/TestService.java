package com.jungle.service.service;

import com.jungle.service.commons.ItemsResult;
import com.jungle.service.repository.mongo.TestDao;
import com.jungle.service.repository.mysql.mybatis.TestUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Jungle
 * @version Created on 2016/5/3.
 */
@Service
public class TestService {
    @Resource
    private TestDao testDao;

    @Resource
    private TestUserDao testUserDao;

    public ItemsResult<Map<String, Object>> fetchAll() {
        List<Map<String, Object>> allList = testDao.findAll();
        ItemsResult<Map<String, Object>> allResult = new ItemsResult<>(allList);
        return allResult;
    }
}
