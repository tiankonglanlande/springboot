package com.yuanfenge.springboot.stater.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Value("${u.username}")
    private String username;
    @Value("${u.pwd}")
    private String pwd;

    @Test
    public void contextLoads() {
        System.out.println("读取username:" + username);
        System.out.println("读取pwd:" + pwd);

    }
}