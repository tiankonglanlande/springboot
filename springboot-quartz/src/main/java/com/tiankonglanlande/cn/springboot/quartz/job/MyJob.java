package com.tiankonglanlande.cn.springboot.quartz.job;

import com.tiankonglanlande.cn.springboot.quartz.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MyJob implements Job {
    @Autowired
    private MyService myService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("任务开始执行了");
        executeTask();
        log.info("任务执行结束了");
    }

    private void executeTask() {
        myService.bizFunction();
    }
}
