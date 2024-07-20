package com.yuanfenge.springboot.mybatisplus.entiry;

import com.baomidou.mybatisplus.annotation.IdType;
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
    // 不设置@TableId(value = "id", type = IdType.AUTO)，批量插入生成的id会出现负数
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private GenderEnum gender;
}
