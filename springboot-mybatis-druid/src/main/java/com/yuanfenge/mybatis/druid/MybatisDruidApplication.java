package com.yuanfenge.mybatis.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuanfenge.mybatis.druid.dao")
public class MybatisDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDruidApplication.class, args);
    }
}
