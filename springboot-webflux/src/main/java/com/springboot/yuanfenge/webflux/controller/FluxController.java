package com.springboot.yuanfenge.webflux.controller;

import com.springboot.yuanfenge.webflux.bean.Student;
import com.springboot.yuanfenge.webflux.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:34
 */
@RestController
@RequestMapping("/student")
public class FluxController {

    @Autowired
    private StuService stuService;

    @RequestMapping("/item")
    public Mono<Student> item(){
        return Mono.just(stuService.selectSimple());
    }

    @RequestMapping("/list")
    public Flux<Student> list(){
        return Flux.fromIterable(stuService.selectList());
    }
}
