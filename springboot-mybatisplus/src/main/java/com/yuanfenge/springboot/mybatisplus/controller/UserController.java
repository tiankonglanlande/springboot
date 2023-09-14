package com.yuanfenge.springboot.mybatisplus.controller;

import com.yuanfenge.springboot.mybatisplus.entiry.User;
import com.yuanfenge.springboot.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 用户控制器
 * @author: 猿份哥
 * @date: 2023/3/24
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 详细的mybatis-plus使用方式：见 test文件夹下的MybatisPlusSampleTest
     * @param name
     * @param gender
     * @return
     */
    @GetMapping("/findUserByName")
    public List<User> findUserByName(@RequestParam(required = true) String name,@RequestParam(required = true,defaultValue = "1")  int gender){
        return userService.listByName(name,gender);
    }
}
