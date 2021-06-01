package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.Order;

public interface OrderDao {
    //将订单插入到数据库中
    void addOrder(Order order);
}
