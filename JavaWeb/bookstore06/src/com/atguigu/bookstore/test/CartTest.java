package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import org.junit.Test;

public class CartTest {
    /*
       测试购物车中所有的方法
    */
    @Test
    public void testCart(){
        //创建两个要购买的图书
        Book book1 = new Book(1, "金瓶梅", "兰陵笑笑生", 0.01, 1000, 1);
        Book book2 = new Book(2, "少年阿宾", "白洁", 0.09, 100, 100);
        //创建购物车对象
        Cart cart = new Cart();
        //买book1
        cart.addBook2Cart(book1);
        //再买一本book1
//        cart.addBook2Cart(book1);
        //买book2
        cart.addBook2Cart(book2);

        //更新book1的数量为4
//        cart.updateCarItem("1","4");

        //删除购物项book2
//        cart.deleteCartItem("2");

        //清空购物车
//        cart.clearCart();

        System.out.println("购物车中图书的总数量是："+cart.getTotalCount());
        System.out.println("购物车中图书的总金额是："+cart.getTotalAmount());
    }
}
