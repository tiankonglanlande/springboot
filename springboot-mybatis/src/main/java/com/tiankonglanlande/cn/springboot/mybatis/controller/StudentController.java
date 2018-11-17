package com.tiankonglanlande.cn.springboot.mybatis.controller;

import com.tiankonglanlande.cn.springboot.mybatis.bean.Student;
import com.tiankonglanlande.cn.springboot.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/students")
    public List<Student> selectStudentList(){
        List<Student> students = studentService.selectStudentList();
        return students;
    }
    @RequestMapping("/students2")
    public List<Student> selectStudentListByAnnotation(){
        List<Student> students = studentService.selectStudentListByAnnotation();
        return students;
    }
}
