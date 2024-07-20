package com.yuanfenge.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户model", description = "请求参数类")
public class User implements Serializable {

    @ApiParam("用户ID编号")
    private Integer id;
    @ApiParam("用户名")
    private String name;
    @ApiParam("年龄")
    private int age;

    public User(String name, int age) {
        this.name=name;
        this.age=age;
    }
}
