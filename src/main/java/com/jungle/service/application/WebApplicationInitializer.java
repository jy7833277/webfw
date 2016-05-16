package com.jungle.service.application;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/4/27.
 */
@Order(1)
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                ApplicationConfig.class,
                WebSecurityConfig.class,
                MongoConfig.class,
                MySQLConfig.class,
                I18NConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
