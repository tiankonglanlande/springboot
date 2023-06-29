package com.yuanfenge.redis.service;

import com.yuanfenge.redis.utils.BatchEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Scan批量操作在数据量大的时候速度优于RedisPipeline
 * @author 猿份哥
 * @description
 * @createTime 2019/8/01 21:00
 */
@Service
public class RedisScanService extends AbsRedisBatch{

    public RedisScanService(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }


    @Override
    public Set<String> batchGet(String pattern) {
        return batchGet(pattern, BatchEnum.DEFAULT_SIZE.getCode());
    }

    /**
     * scan批量查询
     * @param pattern 要查询的key*
     * @param batchSize 每次查询多少batchSize条，直到查询完成
     * @return
     */
    private Set<String> batchGet(String pattern,int batchSize) {
        Set<String> execute = redisTemplate.execute((RedisConnection connection)->{
            Set<String> binaryKeys = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(pattern).count(batchSize).build());
            while (cursor.hasNext()) {
                binaryKeys.add(new String(cursor.next()));
            }
            return binaryKeys;
        });
        return execute;
    }
}
