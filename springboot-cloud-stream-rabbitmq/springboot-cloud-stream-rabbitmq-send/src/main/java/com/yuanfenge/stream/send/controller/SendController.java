package com.yuanfenge.stream.send.controller;
import com.yuanfenge.stream.common.BeanVO;
import com.yuanfenge.stream.send.service.ISendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SendController {

    @Autowired @Qualifier("sendService")
    ISendService sendService;

    @GetMapping("/sendObj")
    public boolean sendObj(@RequestParam(value = "content") String content){
        BeanVO build = BeanVO.builder()
                .sn(UUID.randomUUID().toString().replace("-", ""))
                .content(content)
                .build();
        return  sendService.send(build);
    }

    @GetMapping("/send")
    public boolean send(@RequestParam(value = "content") String content){
        BeanVO build = BeanVO.builder()
                .sn(String.valueOf(System.currentTimeMillis()))
                .content(content)
                .build();
        return  sendService.send(build);
    }

}
