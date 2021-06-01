package com.atguigu.bookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
/*
    1.以后再创建Servlet时让Servlet直接继承BaseServlet，在创建的Servlet中
    不能重新doGet和doPost方法，直接创建的是最终处理请求的方法
    2.每次发送请求时需要传入一个methodName的请求参数
 */
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决POST请求请求中文乱码问题
        request.setCharacterEncoding("UTF-8");
        //获取请求参数methodName的值
        String methodName = request.getParameter("methodName");
        //判断是在登录还是注册
//        if("login".equals(methodName)){
//            //证明在登录，调用login方法
//            login(request,response);
//        }else if("regist".equals(methodName)){
//            //证明在注册，调用regist方法
//            regist(request,response);
//        }

        try {
            //获取Method对象
            /*
                getDeclaredMethod()中参数说明
                    第一个参数：要调用的方法的方法名
                    后面的参数：要调用的方法需要传入的参数的类型
             */
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //设置访问权限
            method.setAccessible(true);
            //调用方法
            /*
                invoke()方法中的参数说明
                    第一个参数：要调用那个对象的方法
                    后面的参数：调用方法时要传入的参数
             */
            method.invoke(this,request,response);
        } catch (Exception e) {
//            e.printStackTrace();
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
