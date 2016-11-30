/*
 * Copyright (c) 2016.  ND.
 * Jungle.
 */

package com.jungle.service.application;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.util.Locale;

/**
 * 多语言配置
 *
 * @author Jungle
 * @version Created on 2016/1/25.
 */
@Configuration
public class I18NConfig {
    @Bean(name = "messageSource")
    public static ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/message");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding(CharEncoding.UTF_8);
        //禁用加载服务器系统语言
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean(name = "localeResolver")
    public static LocaleResolver localeResolver() {
//        SessionLocaleResolver默认URL参数locale设定语言环境
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.CHINA);
//        AcceptHeaderLocaleResolver默认http header中Accept-Language设定语言环境
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        return localeResolver;
    }

//    @Bean(name = "localeChangeInterceptor")
//    public static LocaleChangeInterceptor localeChangeInterceptor() {
//        //与SessionLocaleResolver配合使用，需要配置到拦截器
//        return new LocaleChangeInterceptor();
//    }

    @Bean(name = "messageSourceAccessor")
    public static MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
}
