package com.tiankonglanlande.cn.springboot.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
}
