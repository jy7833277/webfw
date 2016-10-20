package com.jungle.service.controller;

import com.jungle.service.commons.ItemsResult;
import com.jungle.service.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jungle
 * @version Created on 2016/4/27.
 */
@RestController
@RequestMapping(value = "/v0.1/api/test")
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping(value = "/hello")
    public Map<String, Object> test() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("test", "Hello world!");
        return resultMap;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ItemsResult<Map<String, Object>> fetchAllList() {
        return testService.fetchAll();
    }
}
