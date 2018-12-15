package com.tiankonglanlande.cn.springboot;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StaterAppTest {

    @Autowired
    private StringEncryptor stringEncryptor;
    @Value("${u.username}")
    private String puname;
    @Value("${u.pwd}")
    private String ppwd;



    @Test
    public void contextLoads() {
        String username="root-test";
        String pwd="123456890";

        System.out.println("username:"+stringEncryptor.encrypt(username));
        System.out.println("pwd:"+stringEncryptor.encrypt(pwd));
        System.out.println("解密：========");
        System.out.println("username："+stringEncryptor.decrypt(stringEncryptor.encrypt(username)));
        System.out.println("pwd："+stringEncryptor.decrypt(stringEncryptor.encrypt(pwd)));

        System.out.println("解密2：========");
        System.out.println("username："+puname);
        System.out.println("pwd：" + ppwd);

    }

}
