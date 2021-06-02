package com.yuanfenge.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 猿份哥
 */
@Slf4j
@EnableScheduling
@Component
public class MyTask {
    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void delEveryDay() throws Exception {
        log.info("每天凌晨0点开始执行任务");
        //业务代码

    }

    @Scheduled(fixedDelay = 5000)
    public void towTask() {
        System.out.println("5秒后执行定时任务1");
    }

}
