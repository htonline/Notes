<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <%--将base标签包含进来--%>
    <%@ include file="/WEB-INF/include/base.jsp"%>
    <link rel="stylesheet" href="static/css/minireset.css" />
    <link rel="stylesheet" href="static/css/common.css" />
    <link rel="stylesheet" href="static/css/style.css" />
    <link rel="stylesheet" href="static/css/cart.css" />
    <link rel="stylesheet" href="static/css/bookManger.css" />
    <link rel="stylesheet" href="static/css/register.css" />
    <link rel="stylesheet" href="static/css/book_edit.css" />
  </head>
  <body>
    <div class="header">
      <div class="w">
        <div class="header-left">
          <a href="index.html">
            <img src="static/img/logo.gif" alt=""
          /></a>
          <h1>更新图书</h1>
        </div>
      <%@ include file="/WEB-INF/include/header.jsp"%>
      </div>
    </div>
    <div class="login_banner">
      <div class="register_form">
        <form action="BookManagerServlet?methodName=updateBook" method="post">
          <%--更新图书时必须提交图书的id，但是图书的id是不能修改的，所以我们需要通过隐藏域提交图书的id--%>
          <input type="hidden" name="bookId" value="${requestScope.book.id}">
          <div class="form-item">
            <div>
              <label>名称:</label>
              <input type="text" placeholder="请输入名称" name="title" value="${requestScope.book.title}"/>
            </div>
            <span class="errMess" style="visibility: visible;"
              >请输入正确的名称</span
            >
          </div>
          <div class="form-item">
            <div>
              <label>价格:</label>
              <input type="number" placeholder="请输入价格" name="price" value="${requestScope.book.price}"/>
            </div>
            <span class="errMess" style="visibility: visible;">请输入正确数字</span>
          </div>
          <div class="form-item">
            <div>
              <label>作者:</label>
              <input type="text" placeholder="请输入作者" name="author" value="${requestScope.book.author}"/>
            </div>
            <span class="errMess" style="visibility: visible;">请输入正确作者</span>
          </div>
          <div class="form-item">
            <div>
              <label>销量:</label>
              <input type="number" placeholder="请输入销量" name="sales" value="${requestScope.book.sales}"/>
            </div>
            <span class="errMess" style="visibility: visible;">请输入正确销量</span>
          </div>
          <div class="form-item">
            <div>
              <label>库存:</label>
              <input type="number" placeholder="请输入库存" name="stock" value="${requestScope.book.stock}"/>
            </div>
            <span class="errMess" style="visibility: visible;">请输入正确库存</span>
          </div>

          <button class="btn">提交</button>
        </form>
      </div>
    </div>
    <div class="bottom">
      尚硅谷书城.Copyright ©2015
    </div>
  </body>
</html>
