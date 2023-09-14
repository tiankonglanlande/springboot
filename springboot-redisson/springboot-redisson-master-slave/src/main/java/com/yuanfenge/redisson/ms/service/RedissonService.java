package com.yuanfenge.redisson.ms.service;

import java.util.Map;
/**
 * 业务接口
 * @author 猿份哥
 */
public interface RedissonService{

    Map<String, Object> createOrder(Integer productId);
}