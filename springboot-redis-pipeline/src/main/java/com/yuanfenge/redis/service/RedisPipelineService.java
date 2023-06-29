package com.yuanfenge.redis.service;

import com.google.common.collect.Lists;
import com.yuanfenge.redis.utils.BatchEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/01 21:00
 */
@Service
public class RedisPipelineService extends AbsRedisBatch{

    public RedisPipelineService(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }


    @Override
    public void batchSet(List<Map<String, String>> mapList) {
        add(mapList, BatchEnum.DEFAULT_SIZE.getCode(), TimeUnit.MINUTES, 15);
    }

    @Override
    public List<String> batchGet(List<String> keys) {
        return get(keys);
    }

    @Override
    public Set<String> batchGet(String pattern) {
        throw  new UnsupportedOperationException("未实现，暂时不支持");
    }

    private void add(List<Map<String, String>> saveList, int bachSize, TimeUnit unit, int timeout) {
        List<List<Map<String, String>>> partition = Lists.partition(saveList, bachSize);
        partition.stream().forEach(l -> {
            doAdd(l, unit, timeout);
        });
    }

    private void doAdd(List<Map<String, String>> saveList, TimeUnit unit, int timeout) {

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

    private List<String> get(List<String> keyList) {
        List<String> results = new ArrayList<>();
        List<List<String>> partition = Lists.partition(keyList, BatchEnum.DEFAULT_SIZE.getCode());
        partition.stream().forEach(l -> {
            List<String> strings = doGet(l);
            results.addAll(strings);
        });
        return results;
    }


    private List<String> doGet(List<String> keyList) {
        /* 批量获取多条数据 */
        List<Object> objects = redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
            for (String key : keyList) {
                stringRedisConnection.get(key);
            }
            return null;
        });

        List<String> collect = objects.stream().map(val -> String.valueOf(val)).collect(Collectors.toList());

        return collect;
    }
}
