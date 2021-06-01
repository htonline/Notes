package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//处理购物车请求的Servlet
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    //添加图书到购物车
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要添加的图书的id
        String bookId = request.getParameter("bookId");
        //调用BookService中根据图书id获取图书的方法
        Book bookById = bookService.getBookById(bookId);
        //获取Session对象
        HttpSession session = request.getSession();
        //从session域中获取购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            //创建购物车
            cart = new Cart();
            //将购物车放到session域中
            session.setAttribute("cart",cart);
        }
        //将图书添加到购物车中
        cart.addBook2Cart(bookById);
        //获取当前图书在购物项中对应的数量
        CartItem cartItem = cart.getMap().get(bookId);
        //获取购物项中图书的数量
        int count = cartItem.getCount();
        //获取当前图书的库存
        Integer stock = bookById.getStock();
        //判断购物车中图书的数量是否已经超库存
        if(count > stock){
            //将当前购物项图书的数量设置为最大库存
            cartItem.setCount(stock);
            //设置一个提示信息并放到session域中
            session.setAttribute("msg","该图书的库存只有"+stock+"本！");
        }
        //重定向到首页
//        response.sendRedirect(request.getContextPath()+"/index.jsp");
        //重定向到添加购物车时的那一页（从哪儿来回哪儿去）
        //获取请求头中Referer的属性值
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    //清空购物车
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //获取Session域中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        //调用购物车购物车中清空购物车的方法
        if(cart != null){
            //清空购物车
            cart.clearCart();
        }
        //重定向到购物车页面
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

    //删除购物项
    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物项中图书的id
        String bookId = request.getParameter("bookId");
        //获取Session对象
        HttpSession session = request.getSession();
        //获取Session域中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        //调用购物车购物车中清空购物车的方法
        if(cart != null){
            //删除购物项
            cart.deleteCartItem(bookId);
        }
        //重定向到购物车页面
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

    //更新购物项
    protected void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书的id和用户输入的图书的数量
        String bookId = request.getParameter("bookId");
        String bookCount = request.getParameter("bookCount");
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的购物车
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart != null){
            //更新购物项
            cart.updateCarItem(bookId,bookCount);
        }
        //获取购物车中图书的总数量
        int totalCount = cart.getTotalCount();
        //获取购物车中图书的总金额
        double totalAmount = cart.getTotalAmount();
        //获取对应的购物项
        CartItem cartItem = cart.getMap().get(bookId);
        //获取当前购物项中图书的金额小计
        double amount = cartItem.getAmount();
        //创建一个Map
        Map<String , Object> map = new HashMap<>();
        //将三个值放到map中
        map.put("totalCount",totalCount+"");
        map.put("totalAmount",totalAmount+"");
        map.put("amount",amount+"");
        //创建Gson对象
        Gson gson = new Gson();
        //将转成JSON字符串
        String json = gson.toJson(map);
        System.out.println(json);
        response.getWriter().write(json);
        //重定向到购物车页面
//        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }
}
