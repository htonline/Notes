package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.User;

public interface UserDao {

    /**|
     * 根据用户名和密码从数据库中查询对应的记录
     * @param username  用户名
     * @param password  密码
     * @return  User 用户名和密码正确   null 用户名或密码不正确
     */
    User checkUsernameAndPassword(String username , String password);

    /**
     * 根据用户名从数据库中查询对应的记录
     * @param username  用户名
     * @return  true 用户名已存在 false 用户名可用
     */
    boolean checkUsername(String username);

    /**
     * 将用户信息插入到数据库中
     * @param username  用户名
     * @param password  密码
     * @param email 邮箱
     */
    void saveUser(String username , String password , String email);
}
