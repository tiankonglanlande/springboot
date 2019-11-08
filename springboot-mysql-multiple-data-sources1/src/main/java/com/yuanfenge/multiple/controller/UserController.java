package com.yuanfenge.multiple.controller;

import com.yuanfenge.multiple.service.UserService;
import com.yuanfenge.multiple.test1.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/save")
    public int user(UserBean userBean){
        return userService.insertUser(userBean);
    }

    @GetMapping("/save2")
    public String user(){
         userService.save2();
         return "sucess";
    }


    @RequestMapping("/list")
    public List<UserBean> list(){
        return userService.list();
    }

}
