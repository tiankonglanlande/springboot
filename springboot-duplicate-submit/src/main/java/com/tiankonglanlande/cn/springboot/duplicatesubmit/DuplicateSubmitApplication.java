package com.tiankonglanlande.cn.springboot.duplicatesubmit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DuplicateSubmitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuplicateSubmitApplication.class, args);
	}
}
