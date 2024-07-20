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
 *  分布式缓存（列如：redis)
 *      优点：存储容量大，可靠性更好，可在集群间共享
 *      缺点：访问缓存有网络开销
 *      场景：缓存数据量大、可靠性要求高、需要在集群间共享
 *  进程缓存（HashMap、GuvaCache、Coffeine
 *      优点：读取本地缓存，没有网络开销，速度快。
 *      缺点：存储容量有限，可靠性较低，无法共享。
 *      场景：性能要求高、存储数据量较小。
 *
 * @description: Coffeine本地缓存配置（进程缓存）
 * @author: 猿份哥
 * @date: 2023/6/29
 *  驱逐策略：
 *      1.根据最大存储数量驱逐
 *          .maximumSize(10_000)//最大存储个数是10000
 *      2.更据时间驱逐
 *          .expireAfterWrite(Duration.ofMinutes(5))//失效时间是写入后5分钟
 *      3.使用软引用或弱引用（官方不推荐）
 *  驱逐时机：
 *      空闲的时候进行数据清除（不会立马就进行清除，提高效率）。
 *
 *
 * 缓存同步策略
 * 缓存数据同步的三种方式：
 * 1.设置有效期：给缓存设置有效期，到期后自动删除，再次查询时更新
 *      优势：简单方便
 *      缺点：时效性差，缓存过期之前可能不一致
 *      场景：更新频率较低，时效性要求低的业务。
 * 2.同步双写：在修改数据库的同时，直接修改缓存
 *      优势：时效性强，缓存与数据库强一致
 *      缺点：有代码侵入，耦合度高；
 *      场景：对一致性、时效性要求较高的缓存数据
 * 3.异步通知：修改数据库时发送时间通知，相关服务监听到通知后修改缓存数据
 *     （1）基于mq的异步通知方式
 *      优势：低耦合，可以同时通知多个缓存服务
 *      缺点：时效性一般，可能中间不一致状态
 *      场景：时效性要求一般，有多个服务要同步
 *
 *     （2）基于canal异步通知的方式，零侵入，没有任何耦合，时效性更强。效率比较高
 *      优势：零侵入，没有任何耦合，时效性更强，效率比较高
 *      缺点：要部署一个中间件，单独开发
 *      场景：时效性更强
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
