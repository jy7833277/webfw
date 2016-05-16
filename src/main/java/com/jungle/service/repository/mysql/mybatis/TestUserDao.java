package com.jungle.service.repository.mysql.mybatis;

import com.jungle.service.domain.TestUserDomain;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/5/4.
 */
public interface TestUserDao {
    TestUserDomain findUserByName(String userName);
}
