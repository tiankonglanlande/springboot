package com.tiankonglanlande.cn.springboot.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyService {
    public void bizFunction(){
        log.info("MyService执行业务逻辑的方法======================");
    }
}
