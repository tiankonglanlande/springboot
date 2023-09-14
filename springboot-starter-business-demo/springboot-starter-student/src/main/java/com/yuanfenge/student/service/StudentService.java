package com.yuanfenge.student.service;

import com.yuanfenge.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 学生业务类
 * @author: 猿份哥
 * @date: 2023/9/14
 **/
@Service
public class StudentService {

    @Autowired
    Student student;
    public Student findStudent(){
        return student;
    }
}
