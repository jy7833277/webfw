package com.jungle.service.fiilters;

import com.jungle.service.application.common.DefaultJsonMapper;
import com.jungle.service.commons.ErrorResponse;
import com.jungle.service.commons.ResponseErrorMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
 * @Title ExceptionFilter
 * @Package com.jungle.service.fiilters
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        catch (Exception e) {
            Assert.notNull(e);
            Assert.notNull(httpServletRequest);
            Assert.notNull(httpServletResponse);
            ResponseErrorMessage errorMessage = new ResponseErrorMessage(e);
            errorMessage.setMessage(e.getLocalizedMessage());
            errorMessage.setDetail("Stack trace: \n"+getStackTrace(e));
            errorMessage.setCode("SERVICE/INTERNAL_SERVER_ERROR");
            assignRemoteErrorMessage(errorMessage, httpServletRequest);

            ResponseEntity<ResponseErrorMessage> responseEntity = new ResponseEntity<>(errorMessage, null, getHttpStatus(e, httpServletRequest));
            httpServletResponse.setStatus(responseEntity.getStatusCode().value());
            HttpHeaders headers = responseEntity.getHeaders();
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.toSingleValueMap().entrySet()) {
                    httpServletResponse.setHeader(entry.getKey(), entry.getValue());
                }
            }
            try {
                ErrorResponse r = new ErrorResponse(responseEntity.getBody());
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.print(DefaultJsonMapper.toJson(r));
                writer.flush();
                writer.close();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }
    protected String getStackTrace(Throwable throwable) {
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
    protected void assignRemoteErrorMessage(ResponseErrorMessage errorMessage, HttpServletRequest request) {
        errorMessage.setServerTime(new Date());
        errorMessage.setHostId(request.getServerName());
        errorMessage.setRequestId(UUID.randomUUID().toString());
    }
    protected HttpStatus getHttpStatus(Throwable throwable, HttpServletRequest request) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
