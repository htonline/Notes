package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//处理用户登录和注册的Servlet
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    //通过Ajax验证用户名是否存在的方法
    protected void checkUsernameByAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名
        String username = request.getParameter("username");
        //封装User对象
        User user = new User(null, username, null, null);
        //调用UserService中注册的方法
        boolean regist = userService.regist(user);
        response.setContentType("text/html;charset=utf-8");
        if(regist){
            //用户名已存在
            response.getWriter().write("用户名已存在！");
        }else{
            //用户名可用
            response.getWriter().write("<font color='green'>用户名可用！</font>");
        }

    }

    //注销的方法
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //使Session对象失效
        session.invalidate();
        //重定向到首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    //处理用户登录的方法
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装User对象
        User user = new User(null, username, password, null);
        //调用UserService中登录的方法
        User login = userService.login(user);
        //判断查询的User对象是否为null
        if (login != null) {
            //用户名和密码正确，将用户信息放到session域中
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            // 重定向到登录成功页面
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
        } else {
            //用户名或密码不正确，设置一个提示信息并放到request域中
            request.setAttribute("msg", "用户名或密码不正确！");
            // 转发到登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    //处理用户注册的方法
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名、密码、邮箱
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //获取用户输入的验证码
        String inputCode = request.getParameter("code");
        HttpSession session = request.getSession();
        //获取session域中的验证码
        String sessionCode = (String) session.getAttribute("code");
        //判断两者是否相等
        if(inputCode.equals(sessionCode)){
            //证明输入的验证码正确，移除session域中的验证码
            session.removeAttribute("code");
            //正常处理请求
            //封装User对象
            User user = new User(null, username, password, email);
            //调用UserService中注册的方法
            boolean regist = userService.regist(user);
            if (regist) {
                //用户名已存在，注册失败，设置一个提示信息并放到reqeust域中
                request.setAttribute("msg", "用户名已存在！");
                // 转发到注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //用户名可用，将用户信息保存到数据库中
                userService.saveUser(user);
                //重定向到注册成功页面
                response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
            }
        }else{
            //验证码错误，设置一个提示信息并放到request域中
            request.setAttribute("msg","验证码不正确");
            // 转发到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }
}
