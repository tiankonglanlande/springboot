package com.yuanfenge.sentinel.controller;


import com.yuanfenge.commons.result.Result;
import com.yuanfenge.commons.result.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/6/25
 **/
@RestController
public class SentinelController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/redis-sentinel/set")
    public Result redisSentinel(){
        String key = "test:sentinel";
        stringRedisTemplate.opsForZSet().add(key,"测试1",1);
        stringRedisTemplate.opsForZSet().add(key,"测试2",2);

        return  Results.success();
    }
    @GetMapping("/redis-sentinel/get")
    public Result redisSentinelGet(){
        String key = "test:sentinel";

        Set<String> range = stringRedisTemplate.opsForZSet().range(key, 0, 100);
        Map<String,Set<String>> map=new HashMap<>();
        map.put("data",range);

        return Results.success(map);
    }

}
