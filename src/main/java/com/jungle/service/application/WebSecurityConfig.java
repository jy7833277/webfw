package com.jungle.service.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/4/27.
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        ((HttpSecurity)((HttpSecurity)http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).securityContext().and()).anonymous().and();
        Boolean disabledSecurity = true;
        if(!disabledSecurity.booleanValue()) {
            this.onConfigure(http);
        }

    }

    protected void onConfigure(HttpSecurity http) throws Exception {

    }

    @Override
    public void init(WebSecurity web) throws Exception {
//        ((WebSecurity.IgnoredRequestConfigurer) web.ignoring().antMatchers(new String[]{"/$test/**"})).antMatchers
//                (HttpMethod.OPTIONS, new String[]{"/**"});
        super.init(web);
    }
}
