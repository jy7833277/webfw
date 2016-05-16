package com.jungle.service.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/5/4.
 */
@Data
@Entity(name = "user")
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class TestUserDomain {
    @Id
    private String id;
    @Column(name = "username")
    private String userName;
    private String password;
}
