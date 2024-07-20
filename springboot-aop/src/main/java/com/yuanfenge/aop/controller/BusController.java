package com.yuanfenge.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2024/7/18
 **/
@RestController
public class BusController {
    @GetMapping("/findBus")
    public String findBus(){
        //int i = 1 / 0;
        return "findBus result !";
    }
}
