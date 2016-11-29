package com.jungle.service.demo.service;

import com.jungle.service.commons.ItemsResult;
import com.jungle.service.demo.domain.DemoUserDomain;
import com.jungle.service.demo.repository.mongo.DemoDao;
import com.jungle.service.demo.repository.mysql.mybatis.DemoUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Jungle
 * @version Created on 2016/5/3.
 */
@Service
public class DemoService {
    @Resource
    private DemoDao testDao;

    @Resource
    private DemoUserDao demoUserDao;

    public ItemsResult<Map<String, Object>> fetchAll() {
        List<Map<String, Object>> allList = testDao.findAll();
        ItemsResult<Map<String, Object>> allResult = new ItemsResult<>(allList);
        return allResult;
    }

    public List<DemoUserDomain> findAllDemoUsers() {
        List<DemoUserDomain> allDemoUser = demoUserDao.findAllUser();
        return allDemoUser;
    }
}
