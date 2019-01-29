package com.tiankonglanlande.cn.springboot.springbootasync.controller;

import com.tiankonglanlande.cn.springboot.springbootasync.bean.Coffee;
import com.tiankonglanlande.cn.springboot.springbootasync.service.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/14 22:52
 */
@RestController
@RequestMapping("/test")
public class AsyncController {
    @Autowired
    private AsyncTaskService taskService;

    @GetMapping("/task")
    public Object getCoffees(){
        List<Coffee> coffeeList = taskService.getCoffeeList();
        return coffeeList;

    }
    @GetMapping("/asyncTask")
    public Object getAsyncCoffees(){
        List<Coffee> coffeeList = taskService.getAsyncCoffeeList();
        return coffeeList;

    }
}
