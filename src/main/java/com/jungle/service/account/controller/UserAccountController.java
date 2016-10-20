package com.jungle.service.account.controller;

import com.jungle.service.account.domain.UserAccount;
import com.jungle.service.account.service.UserAccountService;
import com.jungle.service.commons.Constants;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * @Title UserAccountController
 * @Package com.jungle.service.account.controller
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
@RestController
@RequestMapping(value = Constants.VERSION_CURRENT + Constants.MAPPING_API + "/account")
public class UserAccountController {
    @Resource
    private UserAccountService userAccountService;
    @RequestMapping(value = "/action/search", method = RequestMethod.GET)
    Page<UserAccount> getSearchedAccounts(@RequestParam(name="key_word", required = false, defaultValue = "") String keyWord,
                                          @RequestParam(name = "type") int type,
                                          @RequestParam(name = "page_num", required = false, defaultValue = ""+Constants.DEFAULT_PAGE_NUM) int pageNum,
                                          @RequestParam(name = "page_size", required = false, defaultValue = ""+Constants.DEFAULT_PAGE_SIZE) int pageSize) {
        Page<UserAccount> accounts = userAccountService.fetchAllAccount(keyWord, type, pageNum, pageSize);
        userAccountService.filterPassword(accounts.getContent());
        return accounts;
    }
}
