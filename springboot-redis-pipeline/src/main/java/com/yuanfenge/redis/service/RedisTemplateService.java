package com.yuanfenge.redis.service;

import com.google.common.collect.Lists;
import com.yuanfenge.redis.utils.BatchEnum;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/** 使用RedisTemplate实现
 * @author 猿份哥
 * @description
 * @createTime 2019/8/01 21:00
 */
@Service
public class RedisTemplateService extends AbsRedisBatch {


    public RedisTemplateService(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }

    /**
     * 批量操作
     * 多线程，异步批量操作：根据hash值分组slot槽位，在进行异步批量操作
     * cluster集群也适合
     *
     * @param mapList
     */
    @Override
    public void batchSet(List<Map<String, String>> mapList) {
        if (CollectionUtils.isEmpty(mapList))
            return;
        List<List<Map<String, String>>> partition = Lists.partition(mapList, BatchEnum.DEFAULT_SIZE.getCode());
        partition.stream().forEach(listMap -> {
            Map<String, String> map = listMap.stream()
                    .flatMap(m -> m.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2));
            redisTemplate.opsForValue().multiSet(map);
        });
    }


    /**
     * 批量操作
     * 多线程，异步批量操作：根据hash值分组slot槽位，在进行异步批量操作
     * cluster集群也适合
     *
     * @param keys
     * @return
     */
    @Override
    public List<String> batchGet(List<String> keys) {
        List<String> result = new ArrayList<>(keys.size());
        if (CollectionUtils.isEmpty(keys))
            return keys;
        List<List<String>> partition = Lists.partition(keys, BatchEnum.DEFAULT_SIZE.getCode());
        partition.stream().parallel().forEach(i -> result.addAll(redisTemplate.opsForValue().multiGet(keys)));
        return result;
    }

}
