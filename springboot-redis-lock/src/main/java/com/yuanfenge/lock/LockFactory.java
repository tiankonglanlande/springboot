package com.yuanfenge.lock;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 锁工厂
 * @author: 猿份哥
 * @date: 2023/4/11
 **/
public enum LockFactory {
    ReentrantLock{
        @Override
        public Lock getLock(Object object, String lockName) {
            return new ReentrantLock();
        }
    },RedisLock{
        @Override
        public Lock getLock(Object stringRedisTemplate, String lockName) {
            return new RedisLock((StringRedisTemplate) stringRedisTemplate,lockName);
        }
    };

    public abstract Lock getLock(Object stringRedisTemplate, String lockName);

}
