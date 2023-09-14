package com.yuanfenge.redisson.single.service;

import java.util.Map;

public interface RedissonService{

    Map<String, Object> createOrder(Integer productId);
}