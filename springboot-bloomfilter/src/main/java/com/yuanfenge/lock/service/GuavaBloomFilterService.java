package com.yuanfenge.lock.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;

/**
 * Guava Bloom filter优点：不用其他中间件，速度快。
 * 误判率默认情况下fpp=0.3。可以调节。fpp值越小误判率越小，但是耗费资源就越多
 *
 * 使用场景：白名单，黑名单，缓存校验（避免缓存穿透，缓存击穿，缓存雪崩）
 *
 * @description: google guava bloomfilter的实现方式
 * @author: 猿份哥
 * @date: 2023/4/8
 **/
@Slf4j
@Service
public class GuavaBloomFilterService {

    public static int SIZE=100*10000;
    //误判率默认0.03
    //private static double fpp=0.003;
    private static double fpp=0.000001;
    //创建guava布隆过滤器
    private static BloomFilter bloomFilter=BloomFilter.create(Funnels.integerFunnel(),SIZE,fpp);


    public void guavaBoolFilter(){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        //1 先让bloomfilter加入到白名单
        for (int i = 0; i < SIZE; i++) {
            bloomFilter.put(i);
        }
        // 故意取10w个不合法的范围的数据来进行误判率演示
        int initialCapacity = 10 * 10000;
        ArrayList list=new ArrayList(initialCapacity);

        for (int i = SIZE+1; i < SIZE+initialCapacity; i++) {
            //验证
            if (bloomFilter.mightContain(i)){
                log.info("被誤判了：{}",i);
                list.add(i);
            }
        }
        log.info("误判总数：{}",list.size());

        stopWatch.stop();
        log.info("总耗时：{}秒",stopWatch.getTotalTimeMillis()/1000);

    }
}
