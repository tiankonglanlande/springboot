package com.yuanfenge.stream.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class ListenerApp {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);
        SpringApplication.run(ListenerApp.class,args);
        latch.await();
    }
}
