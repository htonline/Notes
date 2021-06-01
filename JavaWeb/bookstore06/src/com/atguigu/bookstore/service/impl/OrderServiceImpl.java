package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.*;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String checkout(Cart cart , User user) {
        //获取用户id
        Integer userId = user.getId();
        //创建订单号
        String orderId = System.currentTimeMillis()+userId+"";
        //获取购物车总图书的总数量
        int totalCount = cart.getTotalCount();
        //获取购物车中图书的总金额
        double totalAmount = cart.getTotalAmount();
        //创建Order对象
        Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, userId);
        //将订单插入到数据库中
        orderDao.addOrder(order);

        //保存订单项
        //获取购物车中所有的购物项
        List<CartItem> cartItems = cart.getCartItems();
        //创建两个二维数组
        Object[][] orderItemParams = new Object[cartItems.size()][];
        Object[][] bookParams = new Object[cartItems.size()][];
        
        //遍历
        for (int i = 0 ; i < cartItems.size() ; i ++) {
            //获取购物项
            CartItem cartItem = cartItems.get(i);
            //获取购物项中图书的数量
            int count = cartItem.getCount();
            //获取购物项中图书的金额小计
            double amount = cartItem.getAmount();
            //获取购物项中的Book对象
            Book book = cartItem.getBook();
            //获取书名
            String title = book.getTitle();
            //获取作者
            String author = book.getAuthor();
            //获取图书的价格
            Double price = book.getPrice();
            //获取图书的封面
            String imgPath = book.getImgPath();
            //创建OrderItem对象
//            OrderItem orderItem = new OrderItem(null, count, amount, title, author, price, imgPath, orderId);
            //将订单项插入到数据库中
//            orderItemDao.addOrderItem(orderItem);

            //insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)
            orderItemParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId};

            //获取当前图书的库存和销量
            Integer sales = book.getSales();
            Integer stock = book.getStock();
            //重新设置库存和销量
//            book.setSales(sales+count);
//            book.setStock(stock-count);
            //更新图书信息
//            bookDao.updateBook(book);
            //update books set sales = ? , stock = ? where id = ?
            bookParams[i] = new Object[]{sales+count,stock-count,book.getId()};
        }

        //批量插入订单项
        orderItemDao.batchAddOrderItems(orderItemParams);
        //批量更新图书的库存和销量
        bookDao.batchUpdateSalesAndStock(bookParams);
        //清空购物车
        cart.clearCart();
        return orderId;
    }
}
