package com.yuanfenge.redisson.ms.controller;

import com.yuanfenge.redisson.ms.service.RedissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试controller
 * @author 猿份哥
 */
@RestController
public class RedissonController {
    @Autowired
    RedissonService redissonService;

    /**
     * 测试地址：http://localhost:8080/createOrder/1
     * @param productId
     * @return
     */
    @RequestMapping("/createOrder/{productId}")
    public Map<String,Object> createOrder(@PathVariable int productId){
        return redissonService.createOrder(productId);
    }
}
