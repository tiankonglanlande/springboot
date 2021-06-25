package com.yuanfenge.stream.controller;

import com.yuanfenge.stream.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    DelayService delayService;

    /**
     * 发送延时消息
     * @param content
     * @param time 单位毫秒
     * @return
     */
    @GetMapping("/sendDelayMessage")
    public boolean sendDelayMessage(@RequestParam("content") String content,@RequestParam(value = "time") long time){
        return  delayService.sendDelayMessage(content,time);
    }

    @GetMapping("/sendGeneralMessage")
    public boolean sendGeneralMessage(@RequestParam("content") String content){
        return  delayService.sendGeneralMessage(content);
    }
}
