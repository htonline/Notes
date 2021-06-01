package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    /*
       测试登录的方法
    */
    @Test
    public void testLogin(){
        //创建User对象
        User admin = new User(null, "admin", "123456", null);
        User login = userService.login(admin);
        System.out.println(login);
    }

    /*
       测试注册的方法
    */
    @Test
    public void testRegist(){
        //创建User对象
        User user = new User(null, "admin100", null, null);
        boolean regist = userService.regist(user);
        System.out.println(regist);
    }

    /*
       测试保存用户的方法
    */
    @Test
    public void testSaveUser(){
        //创建User对象
        User user = new User(null, "admin3", "333333", "admin3@163.com");
        userService.saveUser(user);
    }
}
