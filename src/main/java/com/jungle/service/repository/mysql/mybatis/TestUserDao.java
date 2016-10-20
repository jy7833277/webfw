package com.jungle.service.repository.mysql.mybatis;

import com.jungle.service.domain.TestUserDomain;

/**
 * @author Jungle
 * @version Created on 2016/5/4.
 */
public interface TestUserDao {
    TestUserDomain findUserByName(String userName);
}
