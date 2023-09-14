package com.yuanfenge.lock.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * redis bitMap
 * 查询用户最后一次连续签到
 */
@Slf4j
@SpringBootTest
class RedisLockServiceTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    String uid = 111 + "";
    String key = "user:" + uid;
    @Test
    public void sign(){
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth-1,true);

    }

    /**
     * redis bitMap
     * 查询用户最后一次连续签到
     */
    @Test
    void findSign() {

        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();

        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key, BitFieldSubCommands.create()
                        .get(BitFieldSubCommands
                                .BitFieldType
                                .unsigned(dayOfMonth))
                        .valueAt(0));


        if(CollectionUtils.isEmpty(result)){
            return;
        }

        int count=0;
        Long num = result.get(0);
        while (true){
            if ((num & 1)==0){
                break;
            }else {
                count++;
            }
            //右移一位，抛弃最后一位的bit，继续下一位
            num>>>=1;
        }
        System.out.println("最后一次连续签到的次数为：count = " + count);
    }
}