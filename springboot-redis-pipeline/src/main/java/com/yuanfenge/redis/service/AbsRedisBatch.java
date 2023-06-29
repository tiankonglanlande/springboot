package com.yuanfenge.redis.service;

import com.yuanfenge.redis.utils.BatchEnum;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/6/29
 **/
public class  AbsRedisBatch implements IRedisBatch {
    public final StringRedisTemplate redisTemplate;

    public AbsRedisBatch(StringRedisTemplate stringRedisTemplate){
        this.redisTemplate=stringRedisTemplate;
    }

    @Override
    public void batchSet(List<Map<String, String>> mapList) {

    }

    @Override
    public List<String> batchGet(List<String> keys) {
        return null;
    }

    @Override
    public Set<String> batchGet(String pattern) {
        return null;
    }
    /**
     * scan批量刪除
     * @param pattern
     */
    public void batchDeleteUnlink(String pattern) {
        //默认每次删除3000条
        batchDeleteUnlink(pattern, BatchEnum.DEFAULT_SIZE.getCode());
    }
    /**
     * 1. 可以使用unlink 进行异步删除，要配合服务器进行调参
     * redis.conf 的参数修改设置如下：
     * lazyfree-lazy-server-del yes
     * lazyfree-lazy-flush yes
     * lazyfree-lazy-user-del yes
     *
     * 2.使用xargs登录服务器进行删除 【【【【（推荐）】】】】
     * redis-cli -h 127.0.0.1 -p 6379 -a 密码 --scan --pattern "keyPrefix*" | xargs redis-cli -h 127.0.0.1 -p 6379 -a 密码 del
     * redis-cli -h 127.0.0.1 -p 6379 -a test@密码 --scan --pattern "yuanfenge:test:*" | xargs redis-cli -h 127.0.0.1 -p 6379 -a test@dbuser2018 del
     * @param pattern 要刪除的key*
     * @param batchSize 每次刪除batchSize条,直到删除完成
     * @return
     */
    @Override
    public void batchDeleteUnlink(String pattern, int batchSize) {
        redisTemplate.executeWithStickyConnection((RedisConnection connection) -> {
            Cursor<byte[]> scan = connection.scan(new ScanOptions.ScanOptionsBuilder().match(pattern).count(batchSize).build());
            //提高效率：收集需要删除的key到keysToDelete，然后删除
            List<byte[]> keysToDelete = new ArrayList<>();
            scan.forEachRemaining(keysToDelete::add);
            if (!keysToDelete.isEmpty()) {
                connection.unlink(keysToDelete.toArray(new byte[0][]));
            }
            return null;
        });
    }

}
