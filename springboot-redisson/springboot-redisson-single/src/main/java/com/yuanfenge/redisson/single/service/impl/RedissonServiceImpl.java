package com.yuanfenge.redisson.single.service.impl;

import com.yuanfenge.redisson.single.service.RedissonService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedissonServiceImpl implements RedissonService {
    public volatile int num=0;
    String LOCK_PRODUCT_PREFIX="product:";
    @Autowired
    RedissonClient redissonClient;
    @Override
    public Map<String, Object> createOrder(Integer productId) {
        Map<String,Object> resultMap=new HashMap<>();
        RLock lock = redissonClient.getLock(LOCK_PRODUCT_PREFIX+productId);
        lock.lock();
        try {
            // TODO 业务逻辑
            Thread.sleep(500);
            num+=1;
            String content = String.format("i=%d;productId=%s",num,productId);
            System.out.println(content);
            resultMap.put("productId",productId);
            resultMap.put("num",num);
            return resultMap;
        } catch (Exception e) {
            //TODO 错误日志
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

        return null;
    }
}
