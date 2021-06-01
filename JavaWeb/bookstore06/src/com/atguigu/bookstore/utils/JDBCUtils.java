package com.atguigu.bookstore.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
    通过德鲁伊连接池获取连接和释放连接的工具类
    整个JavaWeb阶段都要使用
 */
public class JDBCUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal;

    static {

        try {
            //创建Properties对象
            Properties pro = new Properties();
            //将类路径下的属性文件读成一个流
            InputStream io = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //加载属性文件
            pro.load(io);
            //创建德鲁伊连接池
            dataSource = DruidDataSourceFactory.createDataSource(pro);
            //创建ThreadLocal对象
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接的方法
//    public static Connection getConnection(){
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    //获取连接的方法
    public static Connection getConnection(){
        //从ThreadLocal中获取连接
        Connection connection = threadLocal.get();
        if(connection == null){
            try {
                //从连接池中获取一个连接
                connection = dataSource.getConnection();
                //将连接设置到ThreadLocal中
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

//    public static Connection getConnection(){
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    //释放连接的方法
    public static void releaseConnection(){
       //从ThreadLocal中获取连接
        Connection connection = threadLocal.get();
        if(connection != null){
            try {
                //关闭连接
                connection.close();
                //将关闭的连接从ThreadLocal中移除
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

