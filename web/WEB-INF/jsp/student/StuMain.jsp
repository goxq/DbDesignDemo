<%--
  Created by IntelliJ IDEA.
  User: a9573
  Date: 2020/11/12
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理系统（学生端）</title>

    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet"
          href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <!-- 引入本页的js文件 -->
    <script
            src="${pageContext.request.contextPath }/resources/js/student/StuMain.js"></script>

    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script
            src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <!-- 引入Vue -->
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>

    <!-- 导入前端数据校验插件 -->
    <script src="https://cdn.staticfile.org/nice-validator/1.1.4/jquery.validator.min.js?local=zh-CN"></script>

    <!-- 引入css文件 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/resources/css/Main.css">
</head>

<body>
<div class="top">
    <h2>您好！${sessionScope.user} <%=session.getAttribute("permission").toString().equals("0")?" 管理员":" 学生"%>，欢迎使用图书管理系统</h2>
    <input type="hidden" value="${pageContext.request.contextPath }"
           id="path">
    <input type="hidden" value=${sessionScope.permission}
            id="permission">
    <input type="hidden" value=${sessionScope.user}
            id="user">
</div>
<div class="bottom">
    <div class="index">
        <ul class="nav bg-light nav-pills flex-column nav-justified">
            <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath }/StuBookManagementUIServlet">图书信息</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/StuBorrowManagementUIServlet">我的借阅</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/StuReturnManagementUIServlet">我的归还</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/LogOutServlet" value="logout">退出登录</a></li>
        </ul>
    </div>
    <div class="contain" id="contain">
        <div class="tips">
            点击左边选项来使用本系统
        </div>
    </div>
</div>

</body>
</html>