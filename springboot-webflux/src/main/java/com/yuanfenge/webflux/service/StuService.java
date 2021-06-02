package com.yuanfenge.webflux.service;

import com.yuanfenge.webflux.bean.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:36
 */
@Service
public class StuService {

    public Student selectSimple() {
        return Student.builder()
                .name("猿份哥")
                .age(20)
                .build();
    }

    public List<Student> selectList() {
        List<Student> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student item = Student.builder()
                    .name("猿份哥" + i)
                    .age(20 + i)
                    .build();
            datas.add(item);
        }
        return datas;
    }
}
