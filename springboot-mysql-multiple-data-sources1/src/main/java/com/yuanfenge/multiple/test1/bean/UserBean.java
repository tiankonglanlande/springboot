package com.yuanfenge.multiple.test1.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBean implements Serializable {

    private Integer id;
    private String name;

}
