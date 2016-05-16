package com.jungle.service.repository.mysql.jpa;

import com.jungle.service.domain.TestUserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/5/4.
 */
@Repository
public interface TestUserRepository extends JpaRepository<TestUserDomain, String> {
    TestUserDomain findByUserName(String userName);
}
