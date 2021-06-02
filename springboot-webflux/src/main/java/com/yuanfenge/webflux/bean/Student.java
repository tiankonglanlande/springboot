package com.yuanfenge.webflux.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private String name;
    private int age;
}
