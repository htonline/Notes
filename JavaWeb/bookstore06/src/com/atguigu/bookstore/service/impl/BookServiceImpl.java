package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public Book getBookById(String id) {
        return bookDao.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Page<Book> getPageBooks(String pageNo) {
        //创建Page对象
        Page<Book> page = new Page<>();
        //设置一个默认的页码
        int defaultPageNo = 1;
        try {
            defaultPageNo = Integer.parseInt(pageNo);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //将页码设置到page对象中
        page.setPageNo(defaultPageNo);
        //调用BookDao中分页的方法
        return bookDao.getPageBooks(page);
    }

    @Override
    public Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice) {
        //创建Page对象
        Page<Book> page = new Page<>();
        //设置一个默认的页码
        int defaultPageNo = 1;
        try {
            defaultPageNo = Integer.parseInt(pageNo);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //设置两个默认的价格
        double defaultMinPrice = 0;
        double defaultMaxPrice = Double.MAX_VALUE;
        try {
            defaultMinPrice = Double.parseDouble(minPrice);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        try {
            defaultMaxPrice = Double.parseDouble(maxPrice);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //将页码设置到page对象中
        page.setPageNo(defaultPageNo);
        //调用BookDao中分页和价格范围的方法
        return bookDao.getPageBooksByPrice(page,defaultMinPrice,defaultMaxPrice);
    }
}
