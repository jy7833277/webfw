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
 * @Title ResponseErrorMessage
 * @Package com.jungle.service.commons
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
@Data
public class ResponseErrorMessage {
    private String hostId;
    private String requestId;
    private Date serverTime;
    private String code;
    private String message;
    private String detail;
    private Throwable throwable;
    public ResponseErrorMessage() {}
    public ResponseErrorMessage(Throwable throwable) {
        this.throwable = throwable;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("<");
        builder.append("code:");
        builder.append(getCode());
        builder.append(", message:");
        builder.append(getMessage());
        builder.append(", host_id:");
        builder.append(hostId);
        builder.append(", server_time:");
        builder.append(serverTime);
        builder.append(", request_id:");
        builder.append(requestId);

        builder.append(", detail:");
        builder.append(getDetail());

        builder.append(">");
        return builder.toString();
    }
}
