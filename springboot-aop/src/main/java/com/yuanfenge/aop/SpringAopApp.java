package com.yuanfenge.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title spring aop的日志打印
 * 更深入可参照另一个使用spring AOP实现的简单重试功能的springboot-starter-retry
 * @author: 猿份哥
 **/
@SpringBootApplication
public class SpringAopApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringAopApp.class,args);
        System.out.println("Hello spring aop!");
    }
}