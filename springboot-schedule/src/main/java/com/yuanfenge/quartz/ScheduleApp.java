package com.yuanfenge.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ScheduleApp {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApp.class, args);
    }
}
