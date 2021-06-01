package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        //写sql语句
        String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
        //调用BaseDao中通用的增删改的方法
        update(sql, orderItem.getCount(), orderItem.getAmount(), orderItem.getTitle(), orderItem.getAuthor(), orderItem.getPrice(), orderItem.getImgPath(), orderItem.getOrderId());
    }

    @Override
    public void batchAddOrderItems(Object[][] params) {
        //写sql语句
        String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
        //调用BaseDao中批处理的方法
        batchUpdate(sql,params);
    }
}
