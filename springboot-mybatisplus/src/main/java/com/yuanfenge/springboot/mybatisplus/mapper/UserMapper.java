package com.yuanfenge.springboot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanfenge.springboot.mybatisplus.entiry.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: 猿份哥
 * @date: 2022/10/6
 **/
public interface UserMapper extends BaseMapper<User> {

    @Select("select * FROM t_user WHERE id=#{id}")
    User selectSingle(@Param("id")Integer id);
}
