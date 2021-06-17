package com.yuanfenge.springboot.duplicatesubmit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.yuanfenge.*"})//指定要扫描的包（包含全局异常）
public class DuplicateSubmitApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuplicateSubmitApplication.class, args);
    }
}
