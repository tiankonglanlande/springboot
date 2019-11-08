package com.yuanfenge.multiple.service;

import com.yuanfenge.multiple.test1.bean.UserBean;
import com.yuanfenge.multiple.test2.bean.Order;
import com.yuanfenge.multiple.test2.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    //@Transactional(transactionManager = "test2TransactionManager")
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }
    @Transactional
    public void save2() {
        Order o1=new Order();
        Random random = new Random();
        int random1=random.nextInt(100);
        o1.setId(random1);
        o1.setSn("sn"+random1);
        orderDao.insertOrder(o1);

        //int i=1/0;

        Order o2=new Order();
        int random2=random.nextInt(100);
        o2.setId(random2);
        o2.setSn("sn"+random2);
        orderDao.insertOrder(o2);
    }
    public List<Order> list() {
        return orderDao.list();
    }
}
