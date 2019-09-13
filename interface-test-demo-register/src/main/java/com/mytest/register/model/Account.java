package com.mytest.register.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 3540896336940112885L;
    private Integer id;

    private String name;

    private String userId;

    private String password;

    private String phone;

    private Date createTime;

    private Date updateTime;

}