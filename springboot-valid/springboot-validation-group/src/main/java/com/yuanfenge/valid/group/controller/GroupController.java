package com.yuanfenge.valid.group.controller;

import com.yuanfenge.valid.group.bean.UserBean;
import com.yuanfenge.valid.group.group.AddUserGroup;
import com.yuanfenge.valid.group.group.CustomUseGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
    /**
     * 没有指定分组接口，不会进行任何校验
     * @param user
     * @return
     */
    @GetMapping("/invalid")
    public UserBean invalid(@Validated UserBean user){
        return user;
    }

    /**
     * 测试地址： http://localhost:8080/add?name=%E5%B0%8F%E6%98%8E&age=18
     * @param user
     * @return
     */
    @GetMapping("/add")
    public UserBean userAdd(@Validated(AddUserGroup.class) UserBean user){
        return user;
    }

    /**
     * 测试地址：http://localhost:8080/customValid?status=100
     * 自定义校验器地址：
     * @param user
     * @return
     */
    @GetMapping("/customValid")
    public UserBean customizeValid(@Validated(CustomUseGroup.class) UserBean user){
        return user;
    }
}
