package com.yuanfenge.springboot.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2022/10/6
 **/
@SpringBootApplication
@MapperScan("com.yuanfenge.springboot.mybatisplus.mapper")
public class MybatisPlusStart {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusStart.class,args);
    }
}
