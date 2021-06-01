package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.User;

public interface UserService {
    /**
     * 登录的方法
     * @param user  传入的User对象只包含用户名和密码
     * @return  User 登录成功   null登录失败
     */
    User login(User user);

    /**
     * 注册的方法
     * @param user  传入的User对象中在当前方法中只需要使用用户名
     * @return  true 注册失败   false 注册成功
     */
    boolean regist(User user);

    /**
     * 保存用户的方法
     * @param user  传入的User对象中的用户名、密码、邮箱都会用到
     */
    void saveUser(User user);
}
