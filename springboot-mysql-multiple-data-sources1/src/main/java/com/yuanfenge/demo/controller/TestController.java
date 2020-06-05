package com.yuanfenge.demo.controller;

import com.yuanfenge.demo.bean.Order;
import com.yuanfenge.demo.service.TestService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth 猿份哥
 * @description
 * @createTime 2020 - 5 - 19 16:45
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public Object test(){
        List<Order> list = testService.selectOrder();
        return list;
    }
    @RequestMapping("/test2")
    public Object test2(){
        int i = testService.saveOrder();
        String msg=i>0?"success":"failed";
        return msg;
    }

}
