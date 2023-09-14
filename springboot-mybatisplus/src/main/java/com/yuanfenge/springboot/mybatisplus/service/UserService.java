package com.yuanfenge.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanfenge.springboot.mybatisplus.entiry.User;
import com.yuanfenge.springboot.mybatisplus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户服务
 *  详细的mybatis-plus使用方式：见 test文件夹下的MybatisPlusSampleTest
 * @author: 猿份哥
 * @date: 2023/3/24
 **/
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    public List<User> listByName(String name, int gender){
        List<User> list = lambdaQuery()
                .like(User::getName, name)
                .eq(User::getGender,gender)
                .list();
        return list;
    }


}
