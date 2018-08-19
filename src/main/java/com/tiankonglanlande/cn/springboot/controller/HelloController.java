package com.tiankonglanlande.cn.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/say/{content}")
    public String helloword(@PathVariable("content") String content){

        return content;
    }
}
