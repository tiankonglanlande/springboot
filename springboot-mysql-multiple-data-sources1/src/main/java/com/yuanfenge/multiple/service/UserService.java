package com.yuanfenge.multiple.service;

import com.yuanfenge.multiple.test1.bean.UserBean;
import com.yuanfenge.multiple.test1.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public int insertUser(UserBean userBean) {
        return userDao.insertUser(userBean);
    }

    public List<UserBean> list() {
        return userDao.list();
    }

    @Transactional
    public void save2() {
        UserBean u1=new UserBean();
        Random random = new Random();
        int random1=random.nextInt(100);
        u1.setId(random1);
        u1.setName("name"+random1);
        userDao.insertUser(u1);

        int i=1/0;

        UserBean u2=new UserBean();
        int random2=random.nextInt(100);
        u2.setId(random2);
        u2.setName("name"+random2);
        userDao.insertUser(u2);
    }
}
