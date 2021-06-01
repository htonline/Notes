package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//处理前台图书请求的Servlet
@WebServlet(name = "BookClientServlet",urlPatterns = "/BookClientServlet")
public class BookClientServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    //分页的方法
    protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户输入的页码
        String pageNo = request.getParameter("pageNo");
        //调用BookService中分页的方法
        Page<Book> pageBooks = bookService.getPageBooks(pageNo);
        //将page对象放到request域中
        request.setAttribute("page",pageBooks);
        //转发到显示当前页图书的页面
        request.getRequestDispatcher("/pages/client/books.jsp").forward(request,response);
    }

    //带分页和价格范围查询的方法
    protected void getPageBooksByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取价格范围
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        //获取用户输入的页码
        String pageNo = request.getParameter("pageNo");
        //调用BookService中带分页及价格范围查询的方法
        Page<Book> pageBooksByPrice = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
        //将page对象放到request域中
        request.setAttribute("page",pageBooksByPrice);
        //将价格范围放到request域中
        request.setAttribute("minPrice",minPrice);
        request.setAttribute("maxPrice",maxPrice);
        //转发到显示当前页图书的页面
        request.getRequestDispatcher("/pages/client/books.jsp").forward(request,response);
    }
}
