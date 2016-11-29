package com.jungle.service.demo.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.jungle.service.AbstractTest;
import org.apache.commons.lang3.CharEncoding;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/5/4.
 */
public class DemoControllerTest extends AbstractTest {
    @Test
    public void testGetAll() throws Exception {
        String helloUrl = "/v0.1/api/demo";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.request(HttpMethod.GET, helloUrl)
                .characterEncoding(CharEncoding.UTF_8).accept(MediaType.APPLICATION_JSON).contentType(MediaType
                        .APPLICATION_JSON);
//        request.content(objectMapper.writeValueAsString(object));
        ResultActions result = mockMvc.perform(request);
        MockHttpServletResponse response = result.andReturn().getResponse();
        response.setCharacterEncoding(CharEncoding.UTF_8);

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
        Map<String, Object> map = objectMapper.readValue(response.getContentAsString(), javaType);
        Assert.assertNotNull(map);
        Assert.assertTrue(map.containsKey("count"));
    }

    @Test
    public void testGetAllDemoUsers() throws Exception {
        String helloUrl = "/v0.1/api/demo/users";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.request(HttpMethod.GET, helloUrl)
                .characterEncoding(CharEncoding.UTF_8).accept(MediaType.APPLICATION_JSON).contentType(MediaType
                        .APPLICATION_JSON);
//        request.content(objectMapper.writeValueAsString(object));
        ResultActions result = mockMvc.perform(request);
        MockHttpServletResponse response = result.andReturn().getResponse();
        response.setCharacterEncoding(CharEncoding.UTF_8);

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
        Map<String, Object> map = objectMapper.readValue(response.getContentAsString(), javaType);
        Assert.assertNotNull(map);
        Assert.assertTrue(map.containsKey("count"));
    }
}
