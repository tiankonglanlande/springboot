package com.yuanfenge.business.service;

import com.yuanfenge.annotion.Retry;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2024/7/18
 **/
@Service
public class BusServiceImpl implements BusService{

    @Retry(maxRetryCount = 3,exceptions = {ArithmeticException.class},fallback = "callback")
    @Override
    public String running() {
        int i = 1 / 0;
        return "bus is running now !!!";
    }

    public String callback(){
        System.out.println("callback 调用了");
        return "callback 调用了";
    }
}
