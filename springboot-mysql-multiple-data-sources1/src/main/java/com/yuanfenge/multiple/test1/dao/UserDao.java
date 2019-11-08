package com.yuanfenge.multiple.test1.dao;

import com.yuanfenge.multiple.test1.bean.UserBean;

import java.util.List;


public interface UserDao {
    int insertUser(UserBean user);
    List<UserBean> list();
}
