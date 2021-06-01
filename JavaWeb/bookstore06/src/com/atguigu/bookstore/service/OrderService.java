package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.User;

public interface OrderService {
    //去结账的方法
    String checkout(Cart cart , User user);
}
