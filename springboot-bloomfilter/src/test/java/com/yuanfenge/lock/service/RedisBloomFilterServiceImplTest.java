package com.yuanfenge.lock.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/4/6
 **/
@SpringBootTest
class RedisBloomFilterServiceImplTest {

    @Autowired
    RedisBloomFilterService redisBloomFilterService;
    @Test
    void check() throws InterruptedException {

        long begin = System.currentTimeMillis();

        //初始化数据
        CountDownLatch countDownLatch=new CountDownLatch(300000);
        redisBloomFilterService.initWhiteList(countDownLatch);
        countDownLatch.await();
        //获取测试数据
        ArrayList<String> idList = getMockData();

        idList.stream().forEach(i->{
            //判断是在bloom过滤器中存在
            boolean check = redisBloomFilterService.check(i);
            if (check){
                System.out.println(i+"白名单内-正常访问");
            }else {
                System.out.println(i+"不在白名单内-失败");
            }
        });
        long etime=(System.currentTimeMillis()-begin)/1000;
        System.out.println(String.format("消耗时间为:%s秒",etime));

    }

    private static ArrayList<String> getMockData() {
        ArrayList<String> idList = new ArrayList<>();

        //组装测试用户ID 0-9
        for (int i = 0; i < 10; i++) {
            idList.add(String.valueOf(i));
        }
        //组装测试用户ID 50-54
        for (int i = 50; i < 55; i++) {
            idList.add(String.valueOf(i));
        }
        return idList;
    }
}