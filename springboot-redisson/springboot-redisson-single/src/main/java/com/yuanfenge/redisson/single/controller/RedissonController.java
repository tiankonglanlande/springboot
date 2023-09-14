package com.yuanfenge.redisson.single.controller;

import com.yuanfenge.redisson.single.service.RedissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RedissonController {
    @Autowired
    RedissonService redissonService;
    @RequestMapping("/createOrder/{productId}")
    public Map<String,Object> createOrder(@PathVariable int productId){
        return redissonService.createOrder(productId);
    }
}
