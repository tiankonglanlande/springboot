package com.yuanfenge.lock.entity;

import java.io.Serializable;

/**
 * @description: 用户实体类
 * @author: 猿份哥
 * @date: 2023/4/6
 **/
public class User implements Serializable {

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
