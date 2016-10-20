package com.jungle.service.commons;

import lombok.Data;

import java.util.Date;

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
 * @Title ErrorResponse
 * @Package com.jungle.service.commons
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
@Data
public class ErrorResponse {
    public String hostId;
    public String requestId;
    public Date serverTime;
    public String code;
    public String message;
    public String detail;
    public ResponseErrorMessage cause;
    public ErrorResponse(ResponseErrorMessage errorMessage) {
        hostId = errorMessage.getHostId();
        requestId = errorMessage.getRequestId();
        serverTime = errorMessage.getServerTime();
        code = errorMessage.getCode();
        message = errorMessage.getMessage();
        detail = errorMessage.getDetail();
    }
}
