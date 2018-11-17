package com.wanshifu.backend.applet.quartz;

import com.wanshifu.backend.applet.service.UserUvService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lqm
 * @description 每周更新一次排序
 * @createTime 2018 - 9 - 22 16:16
 */
@Slf4j
public class WeekDelJob implements Job{

    @Autowired
    private UserUvService uvService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void before(){
        log.info("任务开始执行-" + dateFormat.format(new Date()));
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();

        //业务逻辑
        executeTask();

        after();
    }

    private void after(){
        log.info("任务执行结束");
    }

    protected void executeTask() throws JobExecutionException {
        log.info("业务逻辑。。。"+uvService.toString());
        uvService.bizFunction();
    }

}
