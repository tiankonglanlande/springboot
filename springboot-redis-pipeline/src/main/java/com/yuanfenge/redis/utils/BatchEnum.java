package com.yuanfenge.redis.utils;


import lombok.Getter;

/**
 * @description: 常量工具
 * @author: 猿份哥
 * @date: 2023/6/29
 **/
@Getter
public enum BatchEnum {
    DEFAULT_SIZE(3000,"默认批次数量");

    int code;
    String name;

     BatchEnum(int code, String name) {
        this.code=code;
        this.name=name;
    }


}
