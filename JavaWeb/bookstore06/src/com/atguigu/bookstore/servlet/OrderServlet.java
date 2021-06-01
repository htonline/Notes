package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OrderServlet",urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    //去结账
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的用户
        User user = (User)session.getAttribute("user");
        //从session域中获取购物车
        Cart cart = (Cart)session.getAttribute("cart");
        //调用OrderService中去结账的方法
        String orderId = orderService.checkout(cart,user);
        //将订单号放到session域中
        session.setAttribute("orderId",orderId);
        //重定向到显示订单号的页面
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
