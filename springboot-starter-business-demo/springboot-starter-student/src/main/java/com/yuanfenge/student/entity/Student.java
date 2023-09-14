package com.yuanfenge.student.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 学生实体类
 * @author: 猿份哥
 * @date: 2023/9/14
 **/
@Data
@Component
@ConfigurationProperties(prefix = "student")
public class Student {
    private int id;
    private String name;
    private int age;
}
