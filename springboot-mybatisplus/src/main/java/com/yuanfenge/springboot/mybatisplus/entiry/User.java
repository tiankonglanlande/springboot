package com.yuanfenge.springboot.mybatisplus.entiry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuanfenge.springboot.mybatisplus.constant.GenderEnum;
import lombok.Data;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2022/10/6
 **/
@Data
@TableName("t_user")
public class User {
    @TableId
    private Integer id;
    private String name;
    private GenderEnum gender;
}
