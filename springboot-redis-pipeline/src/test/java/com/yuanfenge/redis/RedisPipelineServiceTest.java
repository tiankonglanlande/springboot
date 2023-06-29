package com.yuanfenge.redis;

import com.yuanfenge.redis.service.RedisPipelineService;

import com.yuanfenge.redis.service.RedisScanService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/01 20:35
 */
@SpringBootTest

public class RedisPipelineServiceTest {

    @Autowired
    private RedisPipelineService pipelineService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisScanService redisScanService;

    private String pattern = "yuanfenge:test:";


    @Test
    public void batchGet() throws Exception {
        batchInsert();
        redisPipelineGet();
        //generalBatchGet();
        redisStringBatchGet();
        redisBatchScanGet();
        //redisDeleteKeys();
        unlink();
    }

    @Test
    public void batchInsert() throws Exception {
        long stime = System.currentTimeMillis();
        List<Map<String, String>> saveList = new ArrayList<Map<String, String>>();
        for (int i = 1; i < 1000001; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("key", pattern + i);
            map.put("value", "value值为" + i);
            saveList.add(map);
        }

        pipelineService.batchSet(saveList);
        long etime = System.currentTimeMillis();
        System.out.println("插入100000条数据消耗时间为：" + (etime - stime));

    }

    private void redisPipelineGet() {
        Set<String> keys = stringRedisTemplate.keys(pattern + "*");
        List<String> keyList = new ArrayList<>();
        keys.forEach(i -> {
            keyList.add(i);
        });

        long stime = System.currentTimeMillis();
//        List<String> strings = pipelineService.batchGet(keyList);
        List<String> strings = pipelineService.batchGet(keyList);
        long etime = System.currentTimeMillis();
        System.out.println("string=" + strings.size());
        System.out.println("使用Pipelined消耗时间为：" + (etime - stime));
    }


    public void redisStringBatchGet() {
        Set<String> keys = stringRedisTemplate.keys(pattern + "*");
        List<String> keyList = new ArrayList<>();
        keys.forEach(i -> {
            keyList.add(i);
        });

        long stime = System.currentTimeMillis();
        List<String> strings = stringRedisTemplate.opsForValue().multiGet(keyList);
        long etime = System.currentTimeMillis();
        System.out.println("string=" + strings.size());
        System.out.println("使用multiGet消耗时间为：" + (etime - stime));
    }

    public void generalBatchGet() {
        Set<String> keys = stringRedisTemplate.keys(pattern + "*");
        List<String> keyList = new ArrayList<>();
        keys.forEach(i -> {
            keyList.add(i);
        });

        long stime = System.currentTimeMillis();
        List<String> strings = new ArrayList<>();

        for (String key : keyList) {
            String value = stringRedisTemplate.opsForValue().get(key);
            strings.add(value);
        }
        long etime = System.currentTimeMillis();
        System.out.println("string=" + strings);
        System.out.println("消耗时间为：" + (etime - stime));
    }

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void testHash(){

        HashOperations hashOperations = redisTemplate.opsForHash();
        HashMap map = new HashMap();
        map.put("aa","1");
        map.put("bb","2");
        hashOperations.putAll("product",map);
        String aa = redisTemplate.opsForHash().get("product","aa").toString();
        String bb = redisTemplate.opsForHash().get("product","bb").toString();
        System.out.println(aa);
        System.out.println(bb);




    }

    void redisBatchScanGet() {
        long stime = System.currentTimeMillis();
        Set<String> strings = redisScanService.batchGet(pattern+"*");
        long etime = System.currentTimeMillis();
        System.out.println("string.size=" + strings.size());
        System.out.println("使用Scan消耗时间为：" + (etime - stime));
    }
    void unlink() {
        long stime = System.currentTimeMillis();
        redisScanService.batchDeleteUnlink(pattern+"*");
        long etime = System.currentTimeMillis();

        System.out.println("使用unlink删除数据消耗时间为：" + (etime - stime));
    }

    void redisDeleteKeys() {
        long stime = System.currentTimeMillis();
        Set<String> keys = stringRedisTemplate.keys(pattern);
        stringRedisTemplate.delete(keys);
        long etime = System.currentTimeMillis();
        System.out.println("使用stringRedisTemplate 删除keys消耗时间为：" + (etime - stime));
    }
}