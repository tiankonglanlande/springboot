package com.tiankonglanlande.cn.springboot.mybatis.dao;

import com.tiankonglanlande.cn.springboot.mybatis.bean.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {

    /**
     * 查询所有的学生信息
     * @return
     */
    List<Student> selectStudentList();

    @Select("SELECT * FROM student")
    List<Student> selectStudentListByAnnotation();

}
