package com.yuanfenge.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/6/29
 **/
public interface IRedisBatch {
    /**
     * 批量新增
     * @param mapList
     */
    void batchSet(List<Map<String, String>> mapList);


    /**
     * 根据keys集合批量获取数据
     * @param keys
     * @return
     */
    List<String> batchGet(List<String> keys);


    /**
     * 通过传入pattern进行模糊查询 pattern格式为"test:aa:*"
     * @param pattern
     * @return
     */
    Set<String> batchGet(String pattern);

    /**
     * 使用unlink的方式删除
     * @param pattern
     * @param batchSize
     */
    void batchDeleteUnlink(String pattern, int batchSize);
}
