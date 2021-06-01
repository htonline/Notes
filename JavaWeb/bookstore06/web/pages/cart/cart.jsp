<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="static/script/jquery-1.7.2.js"></script>
    <script>
      $(function () {
        //给清空购物车的超链接绑定单击事件
        $("#clearCart").click(function () {
          //弹出确认或取消的提示框
          return confirm("亲(*^_^*)，您确定要清空购物车吗？┭┮﹏┭┮");
        });
        //给删除购物项的超链接绑定单击事件
        $(".deleteCartItem").click(function () {
           //获取购物项中对应的图书的名字
          var title = $(this).attr("id");
          return confirm("确定要删除《"+title+"》这个购物项吗？┭┮﹏┭┮库😭┭┮﹏┭┮😭");
        });
        //给输入图书数量的文本框绑定change事件
        $(".count-num").change(function () {
          //获取目前购物项中图书的数量的默认值
          var defValue = this.defaultValue;
          //获取用户输入的图书的数量
          var bookCount = $(this).val();
          //获取购物项中对应的图书的id
          var bookId = $(this).attr("id");
          //对用户输入的图书的数量的合法性进行校验
          var countReg = /^\+?[1-9][0-9]*$/;
          if(!countReg.test(bookCount)){
            //将当前购物项中图书的数量恢复成原来的默认值
            this.value = defValue;
            alert("请求输入正整数！");
            return false;
          }
          //获取购物项中图书的库存
          var stock = $(this).attr("name");
          //将stock转为int
          // stock = parseInt(stock);
          stock = new Number(stock);
          //判断用户输入的数量是否超过库存
          if(bookCount > stock){
            alert("该图书的库存只有"+stock+"本！");
            //将图书的数量设置为最大库存
            bookCount = stock;
            //将当前文本框中的值设置为最大库存
            this.value = stock;
            //将默认值修改为最大库存
            this.defaultValue = stock;
          }else{
            //输入的图书的数量是一个合法并且不超过库存的值
            //将默认值修改为现在输入的值
            this.defaultValue = this.value;
          }
          //发送请求
          // location = "CartServlet?methodName=updateCartItem&bookId="+bookId+"&bookCount="+bookCount;
          //设置请求地址
          var url = "CartServlet?methodName=updateCartItem";
          //设置请求参数
          var params = "bookId="+bookId+"&bookCount="+bookCount;
          //获取显示购物项中金额小计的td标签
          var $tdEle = $(this).parent().next().next();
          //发送Ajax请求
          $.post(url,params,function (res) {
            //将购物车中的总数量设置到显示的span标签中
            $("#showTotalCount").text(res.totalCount);
            //将购物车中的总金额设置到显示的span标签中
            $("#showTotalAmount").text(res.totalAmount);
            //购物项中的金额小计设置到显示的td标签中
            $tdEle.text(res.amount);
          },"json");
        });
        //给加减图书的符号绑定单击事件
        $(".count").click(function () {
          //获取id属性，看是在加图书还是减图书
          var operate = $(this).attr("id");
          //获取现在购物项中图书的数量
          var bookCount = $(this).parent().children(":eq(1)").val();
          //获取图书的id
          var bookId = $(this).parent().children(":eq(1)").attr("id");
          //将图书的数量转为数
          bookCount = new Number(bookCount);
          //判断在加还是减
          if (operate == "sub"){
            //将图书的数量减1
            bookCount = bookCount -1;
          }else{
            //将图书的数量加1
            bookCount = bookCount + 1;
          }
          //发送请求
          location = "CartServlet?methodName=updateCartItem&bookId="+bookId+"&bookCount="+bookCount;
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
          <h1>我的购物车</h1>
        </div>
      <%@ include file="/WEB-INF/include/welcome.jsp"%>
      </div>
    </div>
    <div class="list">
      <div class="w">
        <c:if test="${empty sessionScope.cart.cartItems}">
          <center>
          <div>您的购物车空空如也！<a href="index.jsp" style="color: red">购物</a></div>
          </center>
        </c:if>
        <c:if test="${not empty sessionScope.cart.cartItems}">
        <table>
          <thead>
            <tr>
              <th>图片</th>
              <th>商品名称</th>

              <th>数量</th>
              <th>单价</th>
              <th>金额</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${sessionScope.cart.cartItems}" var="carItem">
            <tr>
              <td>
                <img src="${carItem.book.imgPath}" alt="" />
              </td>
              <td>${carItem.book.title}</td>
              <td>
                <span class="count" id="sub">-</span>
                <input name="${carItem.book.stock}" id="${carItem.book.id}" class="count-num" type="text" value="${carItem.count}" />
                <span class="count" id="add">+</span>
              </td>
              <td>${carItem.book.price}</td>
              <td>${carItem.amount}</td>
              <td><a id="${carItem.book.title}" class="deleteCartItem" href="CartServlet?methodName=deleteCartItem&bookId=${carItem.book.id}">删除</a></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <div class="footer">
          <div class="footer-left">
            <a id="clearCart" href="CartServlet?methodName=clearCart" class="clear-cart">清空购物车</a>
            <a href="#">继续购物</a>
          </div>
          <div class="footer-right">
            <div>共<span id="showTotalCount">${sessionScope.cart.totalCount}</span>件商品</div>
            <div class="total-price">总金额<span id="showTotalAmount">${sessionScope.cart.totalAmount}</span>元</div>
            <a class="pay" href="OrderServlet?methodName=checkout">去结账</a>
          </div>
        </div>
        </c:if>
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
