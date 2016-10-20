package com.jungle.service.account.service;

import com.jungle.service.account.domain.UserAccount;
import com.jungle.service.account.repository.mysql.jpa.UserAccountRepository;
import com.jungle.service.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

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
 * @Title UserAccountService
 * @Package com.jungle.service.account.service
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
@Service
public class UserAccountService {
    private static Logger LOG = LoggerFactory.getLogger(UserAccountService.class);
    @Resource
    private UserAccountRepository userAccountRepository;

    public Page<UserAccount> fetchAllAccount(String keyWord, int type, int pageNum, int pageSize) {
        keyWord = null == keyWord ? "" : keyWord.trim();
        if(!StringUtils.isEmpty(keyWord)) {
            keyWord = DataUtil.refactorLikeChar(keyWord);
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<UserAccount> pageResult = userAccountRepository.findByUserNameLikeAndType(keyWord, type, pageable);
        return pageResult;
    }

    public List<UserAccount> filterPassword(List<UserAccount> list) {
        if(!CollectionUtils.isEmpty(list)) {
            for(UserAccount userAccount : list) {
                userAccount.setPassword("");
            }
        }
        return list;
    }
}
