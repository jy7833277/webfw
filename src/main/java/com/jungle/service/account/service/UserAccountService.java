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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;
import java.util.Collection;

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
    @Transactional
    public UserAccount createAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);
        Assert.hasText(userAccount.getUserName());
        Assert.hasText(userAccount.getPassword());
        UserAccount existAccount = userAccountRepository.findByUserName(userAccount.getUserName());
        if(null != existAccount) {
            throw new RestClientException("user_name already exist");
        }
        existAccount = userAccountRepository.findByPhoneNumber(userAccount.getPhoneNumber());
        if(null != existAccount) {
            throw new RestClientException("phone_number already exist");
        }
        userAccount = userAccountRepository.saveAndFlush(userAccount);
        return userAccount;
    }

    @Transactional
    public UserAccount deleteAccount(String id) {
        Assert.hasText(id);
        UserAccount existAccount = userAccountRepository.findOne(id);
        if(null == existAccount) {
            throw new RestClientException("account not exist");
        }
        userAccountRepository.delete(id);
        LOG.info("account-delete, user_name:{}, phone_number:{}, type:{}", existAccount.getUserName(), existAccount.getPhoneNumber(), existAccount.getType());
        return existAccount;
    }
    /**
     * 过滤特殊字符
     * @param obj
     * @return
     */
    public <T> T filterPassword(T obj) {
        if(obj instanceof  UserAccount) {
            UserAccount userAccount = (UserAccount) obj;
            userAccount.setPassword("");
        }
        else if(obj instanceof Collection){
            Collection<UserAccount> list = (Collection<UserAccount>) obj;
            if(!CollectionUtils.isEmpty(list)) {
                for(UserAccount userAccount : list) {
                    userAccount.setPassword("");
                }
            }
        }
        return obj;
    }
}
