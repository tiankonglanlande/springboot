package com.tiankonglanlande.cn.springboot.springbootasync.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/say/{content}/{age}")
    public String helloword(@PathVariable("content") String content,@PathVariable("age") Integer age){
        return content;
    }

}
