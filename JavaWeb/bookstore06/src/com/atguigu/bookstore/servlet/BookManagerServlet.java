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

@WebServlet(name = "BookManagerServlet",urlPatterns = "/BookManagerServlet")
public class BookManagerServlet extends BaseServlet {

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
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    //获取所有图书
//    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /*
//            该方法中实现的步骤：
//            1.调用BookServie中获取所有图书的方法得到一个List
//            2.将List放到request域中
//            3.转发到显示所有图书的页面（pages/manager/book_manager.jsp）
//         */
//        //调用BookService中获取所有图书的方法
//        List<Book> books = bookService.getBooks();
//        //将所有的图书放到热request域中
//        request.setAttribute("books",books);
//        //转发到显示所有图书的页面
//        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
//    }

    //添加图书
//    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取用户输入的图书信息
//        String title = request.getParameter("title");
//        System.out.println(title);
//        String author = request.getParameter("author");
//        String price = request.getParameter("price");
//        String sales = request.getParameter("sales");
//        String stock = request.getParameter("stock");
//        //封装Book对象
//        Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
//        //调用BookService中添加图书的方法
//        bookService.addBook(book);
//        /*
//            注意：
//            添加图书之后，数据库中会多一条记录，需要重新查询数据库，不能直接转发或者重定向
//            到显示所有图书的页面book_manager.jsp
//         */
//        //方式一：直接调用getBooks方法查询
//        getBooks(request,response);
//        //方式二：重定向到查询所有图书的方法
////        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?methodName=getBooks");
//    }

    //删除图书
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要删除的图书的id
        String bookId = request.getParameter("bookId");
        //调用BookService中删除图书的方法
        bookService.deleteBookById(Integer.parseInt(bookId));
        //重新调用getBooks查询所有图书
        getPageBooks(request,response);
    }

    //获取要更新的图书的信息
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要更新的图书的id
        String bookId = request.getParameter("bookId");
        //调用BookService中根据图书id获取一本图书的方法
        Book bookById = bookService.getBookById(bookId);
        //将图书放到reques域中
        request.setAttribute("book",bookById);
        //转发到更新图书的页面
        request.getRequestDispatcher("/pages/manager/book_input.jsp").forward(request,response);
    }

    //添加或更新图书
    protected void addOrUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户更新之后的图书信息
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        //获取要更新的图书的id
        String bookId = request.getParameter("bookId");
        //根据图书的id值判断是在添加图书还是在更新图书
        if("".equals(bookId)){
            //证明在添加图书
            //封装Book对象
            Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
            //调用BookService中添加图书的方法
            bookService.addBook(book);
        }else{
            //证明在更新图书
            //封装Book对象
            Book book = new Book(Integer.parseInt(bookId), title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
            //调用BookService中更新图书的方法
            bookService.updateBook(book);
        }
        //重新调用getBooks查询所有图书
        getPageBooks(request,response);
    }
}
