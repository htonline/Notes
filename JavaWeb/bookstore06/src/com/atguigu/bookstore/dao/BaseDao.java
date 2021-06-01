package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
    通用的增删改查的工具类
 */
public class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    /*
        通用的增删改的方法
     */
    public void update(String sql, Object... params) {
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        //调用queryRunner中的通用的增删改的方法
        try {
            queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
//            e.printStackTrace();
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        } finally {
            //因为QueryRunner最终已经帮我们释放连接，所有我们就不需要通过JDBCUtils工具类手动释放连接
//            JDBCUtils.releaseConnection(connection);
        }
    }

    /*
        查询一个对象的方法
     */
    public <T> T getBean(Class<T> clazz, String sql, Object... params) {
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        T bean = null;
        try {
            //调用queryRunner中的查询得到一个bean的方法
            bean = queryRunner.query(connection, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }finally {
//            JDBCUtils.releaseConnection(connection);
        }
        return bean;
    }

    /*
        查询多个对象的方法
     */
    public <T> List<T> getBeanList(Class<T> clazz, String sql, Object... params) {
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        List<T> list = null;
        try {
            //调用queryRunner中的查询得到一个List的方法
            list = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }finally {
//            JDBCUtils.releaseConnection(connection);
        }
        return list;
    }

    /*
        获取一个单一值的方法（Web中分页时会用到）
            用来执行类似 select count(*) from ...
     */
    public Object getSingleValue(String sql, Object... params) {
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        Object count = null;
        try {
            //调用queryRunner中的查询得到一个单一值的方法
            count = (Long) queryRunner.query(connection, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }finally {
//            JDBCUtils.releaseConnection(connection);
        }
        return count;
    }

    /*
        批处理的方法（Web中去结账的时会用到）
            关于二维数组的说明：
                二维数组的第一维是sql要执行的次数
                二维数组的第二维是执行sql语句时要填充的占位符的值
     */
    public void batchUpdate(String sql, Object[][] params) {
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        try {
            //调用queryRunner中的批处理的方法
            queryRunner.batch(connection, sql, params);
        } catch (SQLException e) {
//            e.printStackTrace();
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }finally {
//            JDBCUtils.releaseConnection(connection);
        }
    }

}
