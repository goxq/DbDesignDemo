<%--
  Created by IntelliJ IDEA.
  User: a9573
  Date: 2020/11/22
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生注册</title>

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
          href="${pageContext.request.contextPath }/resources/css/StuRegister.css">
</head>

<body>

<div class="container">
    <h2>学生注册</h2>
    <form action="${pageContext.request.contextPath}/StuRegisterServlet" method="post">
        <div class="form-group input-group-lg">
            <input type="text" name="stuId" id="user" class="form-control"
                   placeholder="学生ID">
        </div>
        <div class="form-group input-group-lg">

            <input type="password" name="stuPassword" id="password"
                   class="form-control" placeholder="密码">
        </div>
        <div class="form-group input-group-lg">

            <input type="text" name="stuName" id="name"
                   class="form-control" placeholder="姓名">
        </div>
        <div class="form-button">
            <button type="submit" class="btn btn-primary">注册</button>
            <button type="reset" class="btn btn-secondary">重置</button>
        </div>

    </form>
</div>

</body>
</html>
