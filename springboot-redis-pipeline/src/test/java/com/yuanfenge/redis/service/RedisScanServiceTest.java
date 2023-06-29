package com.yuanfenge.redis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/4/18
 **/
@SpringBootTest
class RedisScanServiceTest {

    @Autowired
    RedisScanService redisScanService;
    private String pattern = "yuanfenge:test:";
    @Test
    void batchGet() {
        long stime = System.currentTimeMillis();
        Set<String> strings = redisScanService.batchGet(pattern+"*");
        long etime = System.currentTimeMillis();
        System.out.println("string.size=" + strings.size());
        System.out.println("string=" + strings);
        System.out.println("使用Scan消耗时间为：" + (etime - stime));

    }
    @Test
    void batchDelete() {
        long stime = System.currentTimeMillis();
        redisScanService.batchDeleteUnlink(pattern + "*");
        long etime = System.currentTimeMillis();
        System.out.println("使用Scan消耗时间为：" + (etime - stime));

    }

}