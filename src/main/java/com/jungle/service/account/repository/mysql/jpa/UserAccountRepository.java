package com.jungle.service.account.repository.mysql.jpa;

import com.jungle.service.account.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
 * @Title UserAccountRepository
 * @Package com.jungle.service.account.repository.mysql.jpa
 * <p>
 * *****************************************
 * @Description
 * @date 2016/10/20
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Page<UserAccount> findByUserNameLikeAndType(String keyWord, int type, Pageable pageable);
}
