package com.yuanfenge.student;

import com.yuanfenge.student.controller.StudentController;
import com.yuanfenge.student.entity.Student;
import com.yuanfenge.student.service.StudentService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description: 学生业务自动配置类
 * @author: 猿份哥
 * @date: 2023/9/14
 **/
@Configuration
@Import({StudentController.class,StudentService.class, Student.class})
public class StudentAutoConfiguration {
}
