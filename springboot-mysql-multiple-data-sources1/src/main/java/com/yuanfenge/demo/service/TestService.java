package com.yuanfenge.demo.service;

import com.yuanfenge.demo.bean.Order;
import com.yuanfenge.demo.dao.test01.UserDao;
import com.yuanfenge.demo.dao.test02.OrderDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 猿份哥
 * @description
 * @createTime 2020 -  - 19 16:46
 */
@Service
public class TestService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    public List<Order> selectOrder() {
        return orderDao.selectOrderList();
    }

    public int saveOrder(){
        return orderDao.save(1,"aaaaa");
    }
    public int saveUser(){
        return userDao.save(2,"bbbbb");
    }

}
