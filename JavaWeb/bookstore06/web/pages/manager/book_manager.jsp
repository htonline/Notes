<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <%--将base标签包含进来--%>
    <%@ include file="/WEB-INF/include/base.jsp"%>
    <link rel="stylesheet" href="static/css/minireset.css" />
    <link rel="stylesheet" href="static/css/common.css" />
    <link rel="stylesheet" href="static/css/cart.css" />
    <link rel="stylesheet" href="static/css/bookManger.css" />
    <script src="static/script/jquery-1.7.2.js"></script>
    <script>
      $(function () {
        //给删除图书的超链接绑定单击事件
        $(".del").click(function () {
          //获取图书的名字
          // var title = $(this).parents("tr").children(":eq(1)").text();
          // var title = $(this).parents("tr").children().eq(1).text();
          // var title = $(this).parent().parent().children().eq(1).text();
          //将书名设置到a标签的id属性中，通过获取id属性的值获取书名
          var title = $(this).attr("id");
          //弹出确认或取消的提示框
          // var flag = confirm("确定要删除《"+title+"》这本图书吗？");
          // if(!flag){
          //   //取消默认行为
          //   return false;
          // }
          return confirm("确定要删除《"+title+"》这本图书吗？");
        });
      });
    </script>
  </head>
  <body>
    <div class="header">
      <div class="w">
        <div class="header-left">
          <a href="index.html">
            <img src="static/img/logo.gif" alt=""
          /></a>
          <h1>图书管理系统</h1>
        </div>
        <%@ include file="/WEB-INF/include/header.jsp"%>
      </div>
    </div>
    <div class="list">
      <div class="w">
        <div class="add">
          <a href="pages/manager/book_input.jsp">添加图书</a>
        </div>
        <table>
          <thead>
            <tr>
              <th>图片</th>
              <th>商品名称</th>
              <th>价格</th>
              <th>作者</th>
              <th>销量</th>
              <th>库存</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${requestScope.page.list}" var="book">
            <tr>
              <td>
                <img src="${book.imgPath}" alt="" />
              </td>
              <td>${book.title}</td>
              <td>
                ${book.price}
              </td>
              <td>${book.author}</td>
              <td>${book.sales}</td>
              <td>${book.stock}</td>
              <td>
                <a href="BookManagerServlet?methodName=getBookById&bookId=${book.id}">修改</a>
                <a id="${book.title}" href="BookManagerServlet?methodName=deleteBook&bookId=${book.id}" class="del">删除</a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <div class="footer">
          <div class="footer-right">
          <c:if test="${requestScope.page.pageNo > 1}">
            <div>
              <a href="BookManagerServlet?methodName=getPageBooks&pageNo=1">首页</a>
            </div>
            <div>
              <a href="BookManagerServlet?methodName=getPageBooks&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
            </div>
          </c:if>
            <ul>
            <c:forEach begin="1" end="${requestScope.page.totalPageNo}" var="index">
              <c:if test="${requestScope.page.pageNo == index}">
                <li class="active">
                  <a href="BookManagerServlet?methodName=getPageBooks&pageNo=${index}">${index}</a>
                </li>
              </c:if>
              <c:if test="${requestScope.page.pageNo != index}">
                <li>
                  <a href="BookManagerServlet?methodName=getPageBooks&pageNo=${index}">${index}</a>
                </li>
              </c:if>
            </c:forEach>
            </ul>
          <c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
            <div>
              <a href="BookManagerServlet?methodName=getPageBooks&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
            </div>
            <div>
              <a href="BookManagerServlet?methodName=getPageBooks&pageNo=${requestScope.page.totalPageNo}">末页</a>
            </div>
          </c:if>
            <span>共${requestScope.page.totalPageNo}页</span>
            <span>${requestScope.page.totalRecord}条记录</span>
            <span>到第</span>
            <input type="text" id="pageNoInput"/>
            <span>页</span>
            <button id="pageNoSub">确定</button>
            <script>
              //给确定按钮绑定单击事件
              $("#pageNoSub").click(function () {
                //获取输入的页码
                var pageNo = $("#pageNoInput").val();
                //发送请求
                // window.location.href = "BookManagerServlet?methodName=getPageBooks&pageNo="+pageNo;
                // location.href = "BookManagerServlet?methodName=getPageBooks&pageNo="+pageNo;
                location = "BookManagerServlet?methodName=getPageBooks&pageNo="+pageNo;
              });
            </script>
          </div>
        </div>
      </div>
    </div>
    <div class="bottom">
      <div class="w">
        <div class="top">
          <ul>
            <li>
              <a href="">
                <img src="static/img/bottom1.png" alt="" />
                <span>大咖级讲师亲自授课</span>
              </a>
            </li>
            <li>
              <a href="">
                <img src="static/img/bottom.png" alt="" />
                <span>课程为学员成长持续赋能</span>
              </a>
            </li>
            <li>
              <a href="">
                <img src="static/img/bottom2.png" alt="" />
                <span>学员真是情况大公开</span>
              </a>
            </li>
          </ul>
        </div>
        <div class="content">
          <dl>
            <dt>关于尚硅谷</dt>
            <dd>教育理念</dd>
            <!-- <dd>名师团队</dd>
            <dd>学员心声</dd> -->
          </dl>
          <dl>
            <dt>资源下载</dt>
            <dd>视频下载</dd>
            <!-- <dd>资料下载</dd>
            <dd>工具下载</dd> -->
          </dl>
          <dl>
            <dt>加入我们</dt>
            <dd>招聘岗位</dd>
            <!-- <dd>岗位介绍</dd>
            <dd>招贤纳师</dd> -->
          </dl>
          <dl>
            <dt>联系我们</dt>
            <dd>http://www.atguigu.com</dd>
            <dd></dd>
          </dl>
        </div>
      </div>
      <div class="down">
        尚硅谷书城.Copyright ©2015
      </div>
    </div>
  </body>
</html>
