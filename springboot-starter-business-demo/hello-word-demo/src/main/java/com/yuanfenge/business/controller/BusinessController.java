package com.yuanfenge.business.controller;

import com.yuanfenge.starter.business.BusinessComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @Autowired
    BusinessComponent businessComponent;
    @GetMapping("/join")
    public String join(){
        String result = businessComponent.join();
        return result;
    }

}
