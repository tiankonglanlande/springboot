package com.tiankonglanlande.cn.springboot.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityBean implements Serializable{
    private String province;
    private String city;
    private String district;
}
