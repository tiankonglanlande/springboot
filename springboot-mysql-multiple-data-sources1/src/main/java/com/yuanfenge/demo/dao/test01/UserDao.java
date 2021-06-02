package com.yuanfenge.demo.dao.test01;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    @Insert("insert tbl_user values (#{id},#{name})")
    int save(@Param("id") Integer id, @Param("name") String name);

}
