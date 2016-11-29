package com.jungle.service.demo.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Jungle
 * @version Created on 2016/5/4.
 */
@Data
@Entity(name = "user")
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class DemoUserDomain {
    @Id
    private String id;
    @Column(name = "username")
    private String userName;
    private String password;
}
