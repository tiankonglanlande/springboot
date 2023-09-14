package com.yuanfenge.redisson.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * redisson 集群分布式锁的使用
 * 加锁，解锁，看门狗机制
 * redisson是java juc的分布式实现
 * @author 猿份哥
 */
@SpringBootApplication
public class RedissonMasterSlaveApp {
    public static void main(String[] args) {
        SpringApplication.run(RedissonMasterSlaveApp.class,args);
    }
}