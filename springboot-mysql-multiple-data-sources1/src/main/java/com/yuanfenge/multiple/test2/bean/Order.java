package com.yuanfenge.multiple.test2.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class Order implements Serializable {
    public Integer id;
    public String sn;
}
