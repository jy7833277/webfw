package com.jungle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jungle.service.application.*;
import com.jungle.service.application.common.DefaultJsonMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        classes = {WebMvcConfig.class, WebApplicationInitializer.class, WebSecurityConfig.class,
                ApplicationConfig.class, I18NConfig.class, MongoConfig.class, MySQLConfig.class}
)
@Ignore
public class AbstractTest {
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @Resource
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp() {
        //can define filters
        DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
//        defaultMockMvcBuilder.addFilter(new Filter[]{});
        this.mockMvc = defaultMockMvcBuilder.build();

        this.objectMapper = DefaultJsonMapper.getMapper();
    }
}
