package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.OrderItem;

public interface OrderItemDao {

    //将订单项插入到数据库中
    void addOrderItem(OrderItem orderItem);

    //批量插入订单项的方法
    void batchAddOrderItems(Object[][] params);
}
