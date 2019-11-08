package com.yuanfenge.multiple.test2.dao;
import com.yuanfenge.multiple.test2.bean.Order;

import java.util.List;

public interface OrderDao {
    int insertOrder(Order order);
    List<Order> list();
}
