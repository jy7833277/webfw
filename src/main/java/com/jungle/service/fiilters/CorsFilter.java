package com.jungle.service.fiilters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ******************************************
 * <p>
 * Copyright 2016
 * Jungle All rights reserved
 * <p>
 * *****************************************
 * <p>
 * *** Company ***
 * Jungle
 * <p>
 * *****************************************
 * <p>
 * *** Team ***
 * jungle
 * <p>
 * *****************************************
 *
 * @author Jungle
 * @version V1.0
 * @Title CorsFilter
 * @Package com.jungle.service.fiilters
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // cors.allowed.origins *:Any origin is allowed to access the resource
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE, PATCH");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, Cache-control");
        // cors.preflight.maxage Access-Control-Max-Age The amount of seconds,
        httpServletResponse.addHeader("Access-Control-Max-Age", "1800");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
