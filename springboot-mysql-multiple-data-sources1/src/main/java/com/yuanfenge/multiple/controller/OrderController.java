package com.yuanfenge.multiple.controller;

import com.yuanfenge.multiple.service.OrderService;
import com.yuanfenge.multiple.test1.bean.UserBean;
import com.yuanfenge.multiple.test1.dao.UserDao;
import com.yuanfenge.multiple.test2.bean.Order;
import com.yuanfenge.multiple.test2.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/save")
    public int user(Order order){
        return orderService.insertOrder(order);
    }

    @GetMapping("/save2")
    public String save2(){
        orderService.save2();
        return "ok";
    }


    @RequestMapping("/list")
    public List<Order> list(){
        return orderService.list();
    }

}
