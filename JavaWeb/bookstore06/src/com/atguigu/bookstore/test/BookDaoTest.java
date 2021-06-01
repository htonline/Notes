package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.util.List;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    /*
       测试获取所有图书的方法
    */
    @Test
    public void testGetBooks(){
        List<Book> books = bookDao.getBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /*
       测试添加图书的方法
    */
    @Test
    public void testAddBook(){
        Book book = new Book(null, "三国演义", "罗贯中", 8.88, 100, 10);
        bookDao.addBook(book);
    }

    /*
       测试删除图书的方法
    */
    @Test
    public void testDeleteBookById(){
        bookDao.deleteBookById(36);
    }

    /*
       测试获取一本图书的方法
    */
    @Test
    public void testGetBookById(){
        Book bookById = bookDao.getBookById("1");
        System.out.println(bookById);
    }

    /*
       测试更新图书的方法
    */
    @Test
    public void testUpdateBook(){
        Book book = new Book(30,"教母","马里奥",9.99,1000,10);
        bookDao.updateBook(book);
    }

    /*
       测试分页的方法
    */
    @Test
    public void testGetPageBooks(){
        //创建page对象
        Page<Book> page = new Page<>();
        //设置页码
        page.setPageNo(1);
        //调用BookDao中分页的方法
        Page<Book> pageBooks = bookDao.getPageBooks(page);
        System.out.println("当前页是："+pageBooks.getPageNo());
        System.out.println("总记录数是："+pageBooks.getTotalRecord());
        System.out.println("总页数是："+pageBooks.getTotalPageNo());
        System.out.println("当前页中的记录如下：");
        List<Book> list = pageBooks.getList();
        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void testGetPageBooksByPrice(){
        //创建page对象
        Page<Book> page = new Page<>();
        //设置页码
        page.setPageNo(1);
        //调用BookDao中分页的方法
        Page<Book> pageBooks = bookDao.getPageBooksByPrice(page,10.00,30.00);
        System.out.println("当前页是："+pageBooks.getPageNo());
        System.out.println("总记录数是："+pageBooks.getTotalRecord());
        System.out.println("总页数是："+pageBooks.getTotalPageNo());
        System.out.println("当前页中的记录如下：");
        List<Book> list = pageBooks.getList();
        for (Book book : list) {
            System.out.println(book);
        }
    }

    /*
       测试批量更新图书的库存和销量的方法
    */
    @Test
    public void testBatchUpdateSalesAndStock(){
        //将图书的id为1、2、3的图书的销量更新为100，库存更新为1
        Object[][] params = new Object[3][];
        /*
            填充执行每条sql语句的占位符
            update books set sales = ? , stock = ? where id = ?
         */
        params[0]= new Object[]{100,1,1};
        params[1]= new Object[]{100,1,2};
        params[2]= new Object[]{100,1,3};
        //调用BookDao中批处理的方法
        bookDao.batchUpdateSalesAndStock(params);

    }
}
