package com.yuanfenge.mybatis.druid.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    private String id;
    private String name;
    private String[] parentPhone;//此处修改为数组对象
    private CityBean city;//修改为CityBean类
    private List<VisitedBean> visited;//修改为CityBean类
    private String[] netName;//此处修改为数组对象
}
