package com.yuanfenge.student.controller;

import com.yuanfenge.student.entity.Student;
import com.yuanfenge.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
    @Autowired
    StudentService studentService;



    @GetMapping("/student")
    public Student student(){

        return studentService.findStudent();
    }
}
