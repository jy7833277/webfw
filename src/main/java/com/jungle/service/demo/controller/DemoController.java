package com.jungle.service.demo.controller;

import com.jungle.service.commons.ItemsResult;
import com.jungle.service.demo.domain.DemoUserDomain;
import com.jungle.service.demo.service.DemoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Jungle
 * @version Created on 2016/4/27.
 */
@RestController
@RequestMapping(value = "/v0.1/api/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ItemsResult<Map<String, Object>> fetchAllList() {
        return demoService.fetchAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ItemsResult<DemoUserDomain> fetchAllDemoUsers() {
        List<DemoUserDomain> list = demoService.findAllDemoUsers();
        ItemsResult<DemoUserDomain> result = new ItemsResult<>(list);
        return result;
    }
}
