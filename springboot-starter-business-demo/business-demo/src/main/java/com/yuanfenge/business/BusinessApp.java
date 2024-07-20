package com.yuanfenge.business;

import com.yuanfenge.EnableRetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableStudent
@EnableRetry
public class BusinessApp {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class,args);
    }
}
