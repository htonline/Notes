<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>尚硅谷会员注册页面</title>
    <%--将base标签包含进来--%>
    <%@ include file="/WEB-INF/include/base.jsp"%>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" />
    <link rel="stylesheet" href="static/css/register.css" />
    <style type="text/css">
      .login_form {
        height: 420px;
        margin-top: 25px;
      }
    </style>
    <script src="static/script/jquery-1.7.2.js"></script>
    <script>
      $(function () {
          //给注册按钮绑定单击事件
          $("#sub").click(function () {
              //获取用户输入的用户名
              var username = $("#username").val();
              //设置验证用户名的正则表达式
              var usernameReg = /^[a-zA-Z0-9_-]{3,16}$/;
              //验证用户名是否符合要求
              var flag = usernameReg.test(username);
              if(!flag){
                //将用户名不符合要求的提示信息显示出来
                $("#userMess").css("visibility","visible");
                //取消默认行为
                return false;
              }
              //获取用户输入的密码
              var password = $("#password").val();
              //设置验证密码的正则表达式
              var passwordReg = /^[a-zA-Z0-9_-]{6,18}$/;
              //验证密码是否符合要求
              if(!passwordReg.test(password)){
                //将密码不符合要求的提示信息显示出来
                $("#passwordMess").css("visibility","visible");
                //取消默认行为
                return false;
              }
              //获取确认密码
              var repwd = $("#repwd").val();
              //判断两次输入的密码是否一致
              if(repwd != password){
                //清空确认密码
                $("#repwd").val("");
                $("#repwdMess").css("visibility","visible");
                return false;
              }
              //获取用户输入的邮箱
              var email = $("#email").val();
              //设置验证邮箱的正则表达式
              var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
              //判断邮箱是否合法
              if(!emailReg.test(email)){
                $("#emailMess").css("visibility","visible");
                return false;
              }
              //获取用户输入的验证码
              var code = $("#code").val();
              //判断验证码是否为空
              if(code == ""){
                $("#codeMess").css("visibility","visible");
                return false;
              }
          });
          //给验证码图片绑定单击事件
          $("#imgCode").click(function () {
              //当img标签的src属性值发生变化时，浏览器会重新向新的地址发送请求
              $(this).attr("src","GetCode?t="+Math.random());
          });
          //给文本框绑定内容改变的事件
          $("#username").change(function () {
              //获取用户输入的用户名
              var username = $(this).val();
              //设置请求地址
              var url = "UserServlet?methodName=checkUsernameByAjax";
              //设置请求参数
              var params = "username="+username;
              //发送Ajax请求
              $.post(url,params,function (res) {
                  //将响应信息设置到span标签中
                  $("#msg").html(res);
              },"text");
          });
      });
    </script>
  </head>
  <body>
    <div id="login_header">
      <a href="index.jsp">
        <img class="logo_img" alt="" src="static/img/logo.gif" />
      </a>
    </div>

    <div class="login_banner">
      <div class="register_form">
        <h1>注册尚硅谷会员</h1>
        <span style="color: red" id="msg">${requestScope.msg}</span>
        <form action="UserServlet?methodName=regist" method="post">
<%--          <input type="hidden" name="methodName" value="regist">--%>
          <div class="form-item">
            <div>
              <label>用户名称:</label>
              <input value="${param.username}" type="text" placeholder="请输入用户名" id="username" name="username"/>
            </div>
            <span class="errMess" id="userMess">用户名应为6~16位数组、字母、下划线或减号的组合</span>
          </div>
          <div class="form-item">
            <div>
              <label>用户密码:</label>
              <input type="password" placeholder="请输入密码" id="password" name="password"/>
            </div>
            <span class="errMess" id="passwordMess">密码应为6~18位数组、字母、下划线或减号的组合</span>
          </div>
          <div class="form-item">
            <div>
              <label>确认密码:</label>
              <input type="password" placeholder="请输入确认密码" id="repwd"/>
            </div>
            <span class="errMess" id="repwdMess">两次输入的密码不一致</span>
          </div>
          <div class="form-item">
            <div>
              <label>用户邮箱:</label>
              <input value="${param.email}" type="text" placeholder="请输入邮箱" id="email" name="email"/>
            </div>
            <span class="errMess" id="emailMess">请输入正确的邮箱格式</span>
          </div>
          <div class="form-item">
            <div>
              <label>验证码:</label>
              <div class="verify">
                <input type="text" placeholder="" id="code" name="code"/>
                  <%--将src的值设置为web.xml中配置的url-pattern的路径--%>
                <img id="imgCode" src="GetCode" alt="" style="width: 100px;height: 43px"/>
              </div>
            </div>
            <span class="errMess" id="codeMess">验证码不能为空</span>
          </div>
          <button class="btn" id="sub">注册</button>
        </form>
      </div>
    </div>
    <div id="bottom">
      <span>
        尚硅谷书城.Copyright &copy;2015
      </span>
    </div>
  </body>
</html>
