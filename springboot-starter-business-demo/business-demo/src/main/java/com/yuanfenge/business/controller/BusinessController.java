package com.yuanfenge.business.controller;

import com.yuanfenge.annotion.Retry;
import com.yuanfenge.business.service.BusService;
import com.yuanfenge.starter.business.BusinessComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @Autowired
    BusinessComponent businessComponent;
    @Autowired
    BusService busService;

    @GetMapping("/join")
    @Retry
    public String join(){
        String result = businessComponent.join();
        return result;
    }

    @GetMapping("/retry")
    @Retry
    public String retry(){
        int a=1/0;
        return "retry";
    }
    @GetMapping("/retryService")
    public String retryService(){
        return busService.running();
    }
}
