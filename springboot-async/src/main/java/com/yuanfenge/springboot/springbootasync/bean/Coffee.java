package com.yuanfenge.springboot.springbootasync.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/14 22:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private Integer id;
    private String name;
}
