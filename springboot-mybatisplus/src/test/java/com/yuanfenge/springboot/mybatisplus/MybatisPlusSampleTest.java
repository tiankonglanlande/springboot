package com.yuanfenge.springboot.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuanfenge.springboot.mybatisplus.config.PageConfig;
import com.yuanfenge.springboot.mybatisplus.constant.GenderEnum;
import com.yuanfenge.springboot.mybatisplus.entiry.User;
import com.yuanfenge.springboot.mybatisplus.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2022/10/6
 **/
@SpringBootTest
class MybatisPlusSampleTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * insert: 插入
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setGender(GenderEnum.MAN);
        int insert = userMapper.insert(user);
        System.out.println(insert);

    }

    /**
     * QueryWrapper: 查询多个
     * @throws JsonProcessingException
     */
    @Test
    void testSelect() throws JsonProcessingException {

        List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getId,1969254401));
        System.out.println("users = " + new ObjectMapper().writeValueAsString(users));

    }

    /**
     * 注解方式：查询一个
     * @throws JsonProcessingException
     */
    @Test
    void testSelectSingle() throws JsonProcessingException {

        User user = userMapper.selectSingle(1969254401);
        System.out.println("users = " + new ObjectMapper().writeValueAsString(user));

    }

    /**
     * QueryWrapper方式 查询多个
     * @throws JsonProcessingException
     */
    @Test
    void testSelectQueryWrapper() throws JsonProcessingException {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",1969254401);
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println("users = " + new ObjectMapper().writeValueAsString(users));

    }

    /**
     * Lambda方式 查询多个
     * @throws JsonProcessingException
     */
    @Test
    void testSelectLambdaQueryWrapper() throws JsonProcessingException {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,1969254401);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println("users = " + new ObjectMapper().writeValueAsString(users));

    }

    /**
     * LambdaQueryChainWrapper 查询多个
     * @throws JsonProcessingException
     */
    @Test
    void testSelectLambdaQueryChainWrapper() throws JsonProcessingException {

        LambdaQueryChainWrapper<User> chainWrapper = new LambdaQueryChainWrapper<>(userMapper);
        chainWrapper.eq(User::getId,1969254401);
        List<User> users = chainWrapper.list();
        System.out.println("users = " + new ObjectMapper().writeValueAsString(users));

    }

    /**
     * lambda 查询多个
     * @throws JsonProcessingException
     */
    @Test
    void testSelectQueryWrapperToLambda() throws JsonProcessingException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambda = queryWrapper.lambda();
        lambda.eq(User::getId,1969254401);
        List<User> users = userMapper.selectList(lambda);
        System.out.println("users = " + new ObjectMapper().writeValueAsString(users));
    }

    /**
     * lambda 分页查询  需要注入PaginationInnerInterceptor {@link PageConfig#mybatisPlusInterceptor()}
     */
    @Test
    void selectPage() throws JsonProcessingException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambda = queryWrapper.lambda();
        IPage<User> userPage = userMapper.selectPage(new Page<User>().setCurrent(1).setSize(10), lambda);
        System.out.println("users = " + new ObjectMapper().writeValueAsString(userPage));
    }

}