package com.tiankonglanlande.cn.springboot.mybatis.service;

import com.tiankonglanlande.cn.springboot.mybatis.bean.Student;
import com.tiankonglanlande.cn.springboot.mybatis.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    public List<Student> selectStudentList(){
        return studentDao.selectStudentList();
    }
    public List<Student> selectStudentListByAnnotation(){
        return studentDao.selectStudentListByAnnotation();
    }
}
