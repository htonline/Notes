package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

    //创建UserDao对象
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        //调用UserDao中验证用户名和密码的方法
        User user1 = userDao.checkUsernameAndPassword(user.getUsername(), user.getPassword());
        return user1;
    }

    @Override
    public boolean regist(User user) {
        //调用UserDao中验证用户名是否存在的方法
        boolean flag = userDao.checkUsername(user.getUsername());
        return flag;
    }

    @Override
    public void saveUser(User user) {
        //调用UserDao中保存用户信息的方法
        userDao.saveUser(user.getUsername(),user.getPassword(),user.getEmail());
    }
}
