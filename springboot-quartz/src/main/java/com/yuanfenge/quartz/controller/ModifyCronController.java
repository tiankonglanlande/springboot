package com.yuanfenge.quartz.controller;

import com.yuanfenge.quartz.manager.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 猿份哥
 */
@RestController
public class ModifyCronController {

    @Autowired
    private QuartzManager quartzManager;

    @GetMapping("modify")
    public String modify() throws SchedulerException {
        /**10秒执行一次*/
        String cron = "*/10 * * * * ?";
        quartzManager.pauseJob(QuartzManager.JOB1, QuartzManager.GROUP1);
        quartzManager.modifyJob(QuartzManager.JOB1, QuartzManager.GROUP1, cron);
        return "ok";
    }
}
