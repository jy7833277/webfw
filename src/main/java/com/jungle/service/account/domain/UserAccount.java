package com.jungle.service.account.domain;

import lombok.Data;

import javax.persistence.*;

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
 * @author 杨文军(132500)
 * @version V1.0
 * @Title UserAccount
 * @Package com.jungle.service.account.domain
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
@Data
@Entity(name = "account")
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class UserAccount {
    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    /**
     * 0-super
     * 1-admin
     * 100-user
     */
    private int type;
}
