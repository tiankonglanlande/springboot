package com.yuanfenge.coffeine.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.yuanfenge.coffeine.bean.Order;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 
 * @author: 猿份哥
 * @date:  2023/6/29
 **/
@SpringBootTest
class CoffeineConfigTest {

    /**
     * 缓存订单
     */
    @Autowired
    LoadingCache orderCache;
    /**
     * 缓存明星
     */
    @Autowired
    Cache<String,String> starCache;

    @Autowired
    ObjectMapper mapper;

    @Test
    void orderCache() throws JsonProcessingException {
        List<Order> o = (List<Order>) orderCache.get(CoffeineConfig.KEY_ORDER_LIST);
        System.out.println(mapper.writeValueAsString(o));
    }

    @Test
    void starCache() throws InterruptedException {
        starCache.put("a","周星驰");
        starCache.put("b","杨幂");
        System.out.println(starCache.getAllPresent(Lists.list("a", "b")));

        TimeUnit.SECONDS.sleep(10);
        //因为配置是5秒失效，所以将会打印null
        System.out.println(starCache.getIfPresent("a"));
        System.out.println(starCache.getIfPresent("b"));
    }
}