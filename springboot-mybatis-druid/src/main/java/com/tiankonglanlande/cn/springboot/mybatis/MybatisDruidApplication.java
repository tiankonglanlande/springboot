package com.tiankonglanlande.cn.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tiankonglanlande.cn.springboot.mybatis.dao")
public class MybatisDruidApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisDruidApplication.class, args);
	}
}
