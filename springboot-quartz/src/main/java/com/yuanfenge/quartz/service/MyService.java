package com.yuanfenge.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyService {
    public void bizFunction() throws SchedulerException {
        log.info("MyService执行业务逻辑的方法======================");

    }
}
