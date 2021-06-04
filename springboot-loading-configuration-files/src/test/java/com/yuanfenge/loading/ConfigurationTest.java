package com.yuanfenge.loading;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuanfenge.loading.bean.ExternalStuProperties;
import com.yuanfenge.loading.bean.ExternalStuYml;
import com.yuanfenge.loading.bean.Student;
import com.yuanfenge.loading.bean.StudentProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/17 13:57
 */
@SpringBootTest
public class ConfigurationTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Student student;
    @Autowired
    StudentProperties studentProperties;
    @Autowired
    ExternalStuProperties externalStuProperties;
    @Autowired
    ExternalStuYml externalStuYml;

    /**
     * 加载yml
     *
     * @throws Exception
     */
    @Test
    public void testYml() throws Exception {
        System.out.println("单个key value:" + objectMapper.writeValueAsString(student.getColor()));
        System.out.println("对象信息:" + objectMapper.writeValueAsString(student));
    }

    /**
     * 加载properties
     *
     * @throws Exception
     */
    @Test
    public void testProperties() throws Exception {
        System.out.println("对象信息:" + objectMapper.writeValueAsString(studentProperties));
    }

    /**
     * 加载外部properties
     *
     * @throws Exception
     */
    @Test
    public void testExternalProperties() throws Exception {
        System.out.println("加载外部properties:" + externalStuProperties);
    }

    /**
     * 加载外部yml
     * 如：application-external.yml需要在application中配置
     * spring:
     * profiles:
     * include: external
     *
     * @throws Exception
     */
    @Test
    public void testExternalYml() throws Exception {
        //需要在application中配置
        System.out.println("加载外部yml:" + externalStuYml);
    }

}