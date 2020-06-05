package com.yuanfenge.demo.dao.test02;

import com.yuanfenge.demo.bean.Order;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderDao {


    int save(@Param("id") Integer id, @Param("name") String name);

    @Select("select * from tbl_order")
    List<Order> selectOrderList();
}
