package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.util.Date;

public class OrderTest {

    /*
       测试添加订单和订单项的方法
    */
    @Test
    public void testAdd(){
        //创建Order对象
        Order order = new Order("13888888888", new Date(), 4, 120.00, 0, 1);
        //创建OrderItem对象
        OrderItem orderItem = new OrderItem(null, 1, 30.00, "你好，世界", "詹姆斯·高斯林", 30.00, "static/img/default.jpg", "13888888888");
        OrderItem orderItem2 = new OrderItem(null, 3, 90.00, "你好李焕英", "詹姆斯·贾玲", 30.00, "static/img/default.jpg", "13888888888");

        //创建OrderDao和OrderItemDao对象
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        //保存订单
        orderDao.addOrder(order);
        //保存订单项
        orderItemDao.addOrderItem(orderItem);
        orderItemDao.addOrderItem(orderItem2);
    }
}
