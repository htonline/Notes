package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

import java.util.List;

public interface BookDao {
    //查询数据库中所有的图书
    List<Book> getBooks();
    //将图书信息插入到数据库中
    void addBook(Book book);
    //根据图书的id删除对应的记录
    void deleteBookById(Integer id);
    //根据图书的id从数据库中查询图书信息
    Book getBookById(String id);
    //根据图书的id更新图书信息
    void updateBook(Book book);

    /**
     * 获取带分页的图书信息
     * @param page  只包含pageNo属性值的page对象
     * @return 返回的page对象中所以的属性都有值
     */
    Page<Book> getPageBooks(Page<Book> page);

    /**
     * 获取带分页和价格范围的图书信息
     * @param page  只包含pageNo属性值的page对象
     * @param minPrice  最低价格
     * @param maxPrice  最高价格
     * @return 返回的page对象中所以的属性都有值
     */
    Page<Book> getPageBooksByPrice(Page<Book> page , double minPrice , double maxPrice);
    //批量更新图书的库存和销量
    void batchUpdateSalesAndStock(Object[][] params);
}
