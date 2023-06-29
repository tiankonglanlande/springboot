package com.yuanfenge.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 查询pipeline支持多种类型。mset只支持String类型
 * 删除 推荐unlink,以及使用xargs登录服务器进行删除
 * 详细见{@link com.yuanfenge.redis.service.AbsRedisBatch#batchDeleteUnlink(String, int)} 注释说明
 */
@SpringBootApplication
public class RedisApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }

}
