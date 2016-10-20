package com.jungle.service.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Jungle
 * @version Created on 2016/4/19.
 */
@Configuration
@PropertySource(value = {"classpath:mongo.properties", "classpath:config.properties"})
public class ApplicationConfig {
    /**
     * property文件解析配置
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
