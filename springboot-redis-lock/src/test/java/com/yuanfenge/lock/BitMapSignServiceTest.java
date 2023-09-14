package com.yuanfenge.lock;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2023/6/28
 **/
@SpringBootTest
class BitMapSignServiceTest {
    @Autowired
    BitMapSignService bitMapSignService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void sign() {
        bitMapSignService.sign();
    }

    @Test
    void findSign() {
        int count = bitMapSignService.countSign();
        System.out.println("count = " + count);
    }

    /**
     * 添加uv  统计uv
     */
    @Test
    void hyperLoglog(){
        int size=1000000;
        String[] values=new String[size];
        for (int i = 0; i < size; i++) {
            values[i]=i+"";
        }
        stringRedisTemplate.opsForHyperLogLog().add("uv2",values);

        //统计uv
        Long uv = stringRedisTemplate.opsForHyperLogLog().size("uv2");
        System.out.println("uv = " + uv);
    }

    /**
     * 统计uv
     */
    @Test
    void hyperLogLogCount(){

        //统计uv
        Long uv = stringRedisTemplate.opsForHyperLogLog().size("uv2");
        System.out.println("uv = " + uv);
    }
    /**
     * 统计uv
     */
    @Test
    void hyperLogLogDel(){

        //删除全部uv统计
        stringRedisTemplate.opsForHyperLogLog().delete("uv2");
        System.out.println("删除执行完成！~~");
    }

}