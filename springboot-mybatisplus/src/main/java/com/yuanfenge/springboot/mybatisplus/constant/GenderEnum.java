package com.yuanfenge.springboot.mybatisplus.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2022/10/6
 **/
public enum  GenderEnum{
    UNKNOWN(0,"未知"),
    MAN(1,"男"),
    WOMAN(2,"女");

    @EnumValue
    private final int code;
    private String title;

    GenderEnum(int code, String title) {
        this.code=code;
        this.title=title;
    }

}
