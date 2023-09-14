package com.yuanfenge.coffeine.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.yuanfenge.coffeine.bean.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Coffeine本地缓存配置
 * @author: 猿份哥
 * @date: 2023/6/29
 **/
@Configuration
public class CoffeineConfig {
    public static final String KEY_ORDER_LIST="order_list";

    /**
     * 订单jvm缓存
     * @return
     */
    @Bean
    public LoadingCache<Object, List<Order>> orderCache(){
        return Caffeine.newBuilder()
                .maximumSize(10_000)//最大存储个数是10000
                .expireAfterWrite(Duration.ofMinutes(5))//失效时间是5分钟
                .refreshAfterWrite(Duration.ofMinutes(1))//每分钟刷新一次
                .build(key -> readFromDb());//不存在则触发查询接口
    }

    @Bean
    public Cache<String,String> starCache(){
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(5))//失效效时间是5秒
                .build();
    }

    private List<Order> readFromDb() {
        System.out.println("调用方法 start：readFromDb加载数据");
        List<Order> orders=new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            orders.add(Order.builder()
                            .id(i)
                            .name(String.format("order%d",i))
                    .build());
        }
        System.out.println("调用方法 end：readFromDb加载数据");
        return orders;
    }
}
