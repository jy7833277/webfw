package com.jungle.service.account.service;

import com.jungle.service.AbstractTest;
import com.jungle.service.account.domain.UserAccount;
import com.jungle.service.util.EncryptUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

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
 * @Title UserAccountTest
 * @Package com.jungle.service.account.service
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
public class UserAccountTest extends AbstractTest {
    @Resource
    private UserAccountService userAccountService;

    @Test
    public void testAll() {
        UserAccount userAccount = createAccount();
        Assert.assertNotNull(userAccount);
        Assert.assertNotNull(userAccount.getId());
        deleteAccount(userAccount.getId());
    }

    public UserAccount createAccount() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("junit-test");
        userAccount.setPassword(EncryptUtil.encryptMD5_Salt("test"));
        userAccount.setType(100);
        userAccount.setPhoneNumber("100000000000");
        userAccount = userAccountService.createAccount(userAccount);
        return userAccount;
    }

    public void deleteAccount(String id) {
        userAccountService.deleteAccount(id);
    }
}
