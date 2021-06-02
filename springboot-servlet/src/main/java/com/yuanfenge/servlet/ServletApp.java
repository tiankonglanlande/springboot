package com.yuanfenge.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.yuanfenge.springboot.servlet.servlet")
public class ServletApp {

    public static void main(String[] args) {
        SpringApplication.run(ServletApp.class, args);
    }
}
