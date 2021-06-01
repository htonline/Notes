package com.atguigu.bookstore.filter;

import com.atguigu.bookstore.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//判断是否登录的过滤器
@WebFilter(filterName = "LoginFilter",urlPatterns = "/OrderServlet")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的User对象
        User user = (User)session.getAttribute("user");
        //判断User对象是否为null
        if(user != null){
            //证明已经登录，放行请求
            chain.doFilter(req, resp);
        }else{
            //证明用户还没有登录，设置一个提示信息并放到request域中
            request.setAttribute("msg","该操作需要先登录！");
            //转发到登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
