package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public List<Book> getBooks() {
        //写sql语句
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from books";
        //调用BaseDao中获取一个List的方法
        List<Book> beanList = getBeanList(Book.class, sql);
        return beanList;
    }

    @Override
    public void addBook(Book book) {
        //写sql语句
        String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        //调用BaseDao中通用的增上该的方法
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public void deleteBookById(Integer id) {
        //写sql语句
        String sql = "delete from books where id = ?";
        //调用BaseDao中通用的增删改的方法
        update(sql,id);
    }

    @Override
    public Book getBookById(String id) {
        //写sql语句
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from books where id = ?";
        //调用BaseDao中获取一个对象的方法
        Book bean = getBean(Book.class, sql, id);
        return bean;
    }

    @Override
    public void updateBook(Book book) {
        //写sql语句
        String sql = "update books set title=?,author=?,price=?,sales=?,stock=? where id = ?";
        //调用BaseDao中通用的增删改的方法
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getId());
    }

    @Override
    public Page<Book> getPageBooks(Page<Book> page) {
        //获取数据库中图书的数量
        String sql1 = "select count(*) from books";
        //调用BaseDao中获取一个单一值的方法
        long totalRecord = (long)getSingleValue(sql1);
        //将总记录数设置到page对象中
        page.setTotalRecord((int)totalRecord);

        //获取当前页码中的记录数
        String sql2 = "select id,title,author,price,sales,stock,img_path imgPath from books limit ?,?";
        //调用BaseDao中查询一个List的方法
        List<Book> beanList = getBeanList(Book.class, sql2, (page.getPageNo() - 1) * Page.getPageSize(), Page.getPageSize());
        //将List设置到page对象中
        page.setList(beanList);
        return page;
    }

    @Override
    public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice) {
        //获取数据库中图书的数量
        String sql1 = "select count(*) from books where price between ? and ?";
        //调用BaseDao中获取一个单一值的方法
        long totalRecord = (long)getSingleValue(sql1,minPrice,maxPrice);
        //将总记录数设置到page对象中
        page.setTotalRecord((int)totalRecord);
        //获取当前页码中的记录数
        String sql2 = "select id,title,author,price,sales,stock,img_path imgPath from books where price between ? and ? limit ?,?";
        //调用BaseDao中查询一个List的方法
        List<Book> beanList = getBeanList(Book.class, sql2, minPrice,maxPrice,(page.getPageNo() - 1) * Page.getPageSize(), Page.getPageSize());
        //将List设置到page对象中
        page.setList(beanList);
        return page;
    }

    @Override
    public void batchUpdateSalesAndStock(Object[][] params) {
        //写sql语句
        String sql = "update books set sales = ? , stock = ? where id = ?";
        //调用BaseDao中批处理的方法
        batchUpdate(sql,params);
    }
}
