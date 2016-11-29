package com.jungle.service.demo.repository.mysql.mybatis;

import com.jungle.service.demo.domain.DemoUserDomain;

import java.util.List;

/**
 * @author Jungle
 * @version Created on 2016/5/4.
 */
public interface DemoUserDao {
    DemoUserDomain findUserByName(String userName);
    List<DemoUserDomain> findAllUser();
}
