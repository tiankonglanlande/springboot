package com.yuanfenge.lock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Redis bloomfilter的实现方式
 *
 * 使用场景：白名单，黑名单，缓存校验
 * @description: 用户服务
 * @author: 猿份哥
 * @date: 2023/4/6
 **/
@Service
public class RedisBloomFilterService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    String BLOOM_WHITE_LIST ="user:whitelist";
    String USER_ID_KEY ="userid_%s";

    /**
     * 初始换白名单数据,加载到bloom过滤器
     */
    public void initWhiteList(CountDownLatch countDownLatch){
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < countDownLatch.getCount(); i++) {
            String userId=i+"";
            executorService.execute(()->{
                addData(userId);
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }
    }

    /**
     * 添加到bloom过滤器
     * @param userId
     */
    public void addData(String userId){
        long offset = getBloomParam(userId);
        stringRedisTemplate.opsForValue().setBit(BLOOM_WHITE_LIST,offset,true);
    }

    /**
     * 检查bloom过滤器中是否存在
     * @param id
     * @return
     */
    public boolean check(String id){
        long offset = getBloomParam(id);
        Boolean bit = stringRedisTemplate.opsForValue().getBit(BLOOM_WHITE_LIST, offset);
        return bit;
    }

    public long getBloomParam(String id){
        String hk= String.format(USER_ID_KEY,id);
        //将hk进行hashcode
        int hash =Math.abs(hk.hashCode());
        //得到hash槽位
        long offset = (long)(hash % Math.pow(2, 32));
        return offset;
    }

}
