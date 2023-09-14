package com.yuanfenge.coffeine.bean;

import lombok.*;

/**
 * @description: 订单
 * @author: 猿份哥
 * @date: 2023/6/29
 **/
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String name;
}
