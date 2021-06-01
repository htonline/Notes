package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void addOrder(Order order) {
        //写sql语句
        String sql = "insert into orders(id,order_time,total_count,total_amount,state,user_id) values(?,?,?,?,?,?)";
        //调用BaseDao中通用的增删改的方法
        update(sql,order.getId(),order.getOrderTime(),order.getTotalCount(),order.getTotalAmount(),order.getState(),order.getUserId());
    }
}
