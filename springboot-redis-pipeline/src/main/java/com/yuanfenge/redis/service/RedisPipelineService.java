package com.yuanfenge.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/01 21:00
 */
@Service
public class RedisPipelineService {

    @Autowired
    StringRedisTemplate redisTemplate;

    public void batchInsert(List<Map<String, String>> saveList, TimeUnit unit, int timeout) {
        /* 插入多条数据 */
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (Map<String, String> needSave : saveList) {
                    redisTemplate.opsForValue().set(needSave.get("key"), needSave.get("value"), timeout, unit);
                }
                return null;
            }
        });
    }

    public List<String> batchGet(List<String> keyList) {
        /* 批量获取多条数据 */
        List<Object> objects = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
                for (String key : keyList) {
                    stringRedisConnection.get(key);
                }
                return null;
            }
        });

        List<String> collect = objects.stream().map(val -> String.valueOf(val)).collect(Collectors.toList());

        return collect;
    }
}
