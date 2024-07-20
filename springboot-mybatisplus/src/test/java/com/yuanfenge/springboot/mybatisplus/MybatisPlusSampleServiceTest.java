package com.yuanfenge.springboot.mybatisplus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuanfenge.springboot.mybatisplus.constant.GenderEnum;
import com.yuanfenge.springboot.mybatisplus.entiry.User;
import com.yuanfenge.springboot.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意：mybatis-plus的批量插入操作（实际上是单个循环操作）并不是insert()values(),()...的批量操作
 * 解决方法：1.（未验证）配置数据url拼接 rewriteBatchedStatements=true
 *         2.自己编写sql语句<foreach></foreach>
 * @description:
 * @author: 猿份哥
 * @date: 2022/10/6
 **/
@SpringBootTest
class MybatisPlusSampleServiceTest {

    @Autowired
    private UserService userService;

    /**
     * insert: 单个插入
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setGender(GenderEnum.MAN);
        boolean save = userService.save(user);
        System.out.println(user);
    }
    /**
     * insert: 多个插入（注意根据源码并不是真正的批量插入）
     * 注意：mybatis-plus的批量插入操作（实际上是单个循环操作）并不是insert()values(),()...的批量操作
     * 解决方法：1.（未验证）配置数据url拼接 rewriteBatchedStatements=true
     *         2. 自己编写sql语句<foreach></foreach>
     */
    @Test
    void testMultiInsert() throws JsonProcessingException {
        List<User> userList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("张三"+i);
            user.setGender(GenderEnum.MAN);
            userList.add(user);
        }
        boolean save = userService.saveBatch(userList);
        System.out.println("保存多个："+new ObjectMapper().writeValueAsString(userList));
    }

    @Test
    void testSelectList() throws JsonProcessingException {
        List<User> userList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("张三"+i);
            user.setGender(GenderEnum.MAN);
            userList.add(user);
        }
        List<User> list = userService.list();
        System.out.println("查询list："+new ObjectMapper().writeValueAsString(list));
    }



}