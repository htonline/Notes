package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    /*
       测试验证用户名和密码是否正确的方法
    */
    @Test
    public void testCheckUsernameAndPassword(){
        User admin = userDao.checkUsernameAndPassword("admin2", "123456");
        System.out.println(admin);
    }

    /*
       测试验证用户名是否存在的方法
    */
    @Test
    public void testCheckUsername(){
        boolean admin = userDao.checkUsername("admin2");
        System.out.println(admin);
    }

    /*
       测试保存用户信息的方法
    */
    @Test
    public void testSaveUser(){
        userDao.saveUser("admin2","222222","admin2@atguigu.com");
    }
}
