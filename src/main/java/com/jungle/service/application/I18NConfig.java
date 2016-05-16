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
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * 多语言配置
 *
 * @author 杨文军(132500)
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
        return messageSource;
    }

    @Bean(name = "localeChangeInterceptor")
    public static LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    @Bean(name = "messageSourceAccessor")
    public static MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
}
