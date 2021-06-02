package com.yuanfenge.springboot.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventApp {

    public static void main(String[] args) {
        SpringApplication.run(EventApp.class, args);
    }
}
