package com.tiankonglanlande.cn.springboot.jasypt.simple;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptSimpleApplicationTests {

	@Autowired
	private StringEncryptor stringEncryptor;

	@Value("u.username")
	private String username;
	@Value("u.pwd")
	private String pwd;

	@Test
	public void jiemi(){
		System.out.println("username解密后:"+username);
		System.out.println("pwd解密后:"+pwd);
	}

	@Test
	public void jiami() {
		String username="tiankonglanlande";
		String pwd="tiankonglanlande_pwd";

		System.out.println("username加密串:"+stringEncryptor.encrypt(username));
		System.out.println("pwd加密串:"+stringEncryptor.encrypt(pwd));
		System.out.println("username原始值:"+username);
		System.out.println("pwd原始值:"+pwd);

	}





}

