package com.yuanfenge.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 使用 BitMap实现签到功能
 * @description:
 * @author: 猿份哥
 * @date: 2023/6/28
 **/
@Service
public class BitMapSignService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    String uid = 111 + "";
    String key = "user:" + uid;

    /**
     * 用户签到
     */
    public void sign(){
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth-1,true);
    }
    /**
     * redis bitMap
     * 查询用户最后一次连续签到的次数统计
     */
    int countSign() {

        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();

        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key, BitFieldSubCommands.create()
                        .get(BitFieldSubCommands
                                .BitFieldType
                                .unsigned(dayOfMonth))
                        .valueAt(0));


        if(CollectionUtils.isEmpty(result)){
            return 0;
        }

        // 000000111000011111  将结果&1判断等于0表示不是连续签到了，
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
        return count;
    }


}
