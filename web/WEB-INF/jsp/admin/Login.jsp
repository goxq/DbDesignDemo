<%--
  Created by IntelliJ IDEA.
  User: a9573
  Date: 2020/11/12
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请登录</title>

    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet"
          href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script
            src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <!-- 引入css样式文件 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/resources/css/Login.css">
</head>

<body>

<div class="container">
    <h2>图书管理系统</h2>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <div class="form-group input-group-lg">
            <input type="text" name="user" id="user" class="form-control"
                   placeholder="管理员姓名">
        </div>
        <div class="form-group input-group-lg">

            <input type="password" name="password" id="password"
                   class="form-control" placeholder="密码">
        </div>
        <div class="form-check">
            <label>
                <input class="form-check-input" type="checkbox"name="rememberMe" >记住我
            </label>
        </div>
        <div class="form-button">
            <button type="submit" class="btn btn-primary">登录</button>
            <button type="reset" class="btn btn-secondary">重置</button>
        </div>

    </form>
</div>

</body>
</html>
