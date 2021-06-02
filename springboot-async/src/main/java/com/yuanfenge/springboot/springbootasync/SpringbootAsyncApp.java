package com.yuanfenge.springboot.springbootasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class SpringbootAsyncApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAsyncApp.class, args);
    }

    @Bean
    public Executor threadPoolExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(2);
        pool.setMaxPoolSize(2);
        pool.setQueueCapacity(500);
        pool.setThreadNamePrefix("猿份哥-group-");
        pool.initialize();
        return pool;
    }
}

