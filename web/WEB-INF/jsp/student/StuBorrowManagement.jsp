<%--
  Created by IntelliJ IDEA.
  User: a9573
  Date: 2020/11/17
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">

    <!-- 引入css样式表 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/resources/css/BorrowManagement.css">

    <!-- 引入JS文件 -->
    <script
            src="${pageContext.request.contextPath }/resources/js/student/StuBorrowManagement.js"></script>
</head>
<body>
<div class = "book-top">
    <div class = "book-title">
        <h3>我的借阅</h3>
    </div>
    <div class = "book_functionArea">
        <div class="book-search input-group-lg">
            <input type="text" class = "borrow-search-input" placeholder="输入书籍号来查询">
            <button class = "btn btn-secondary book-search-button"
                    id="book-search-button" @click="searchBook()">搜索</button>
        </div>
    </div>
</div>
<!--书籍信息列表-->
<div class="content" id="content">
    <table class="table table-condensed table-hover" id="table-content">
        <thead>
        <tr>
            <th>学号</th>
            <th>书籍编号</th>
            <th>学生姓名</th>
            <th>书名</th>
            <th>借阅日期</th>
            <th>应还日期</th>
            <th>实还日期</th>
            <th>逾期情况</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(borrow,index) in borrowList">
            <td>{{borrow.stuId}}</td>
            <td>{{borrow.bookId}}</td>
            <td>{{borrow.stuName}}</td>
            <td>{{borrow.bookName}}</td>
            <td>{{borrow.borrowDate}}</td>
            <td>{{borrow.expectReturnDate}}</td>
            <td v-if="borrow.realReturnDate==null">尚未归还</td>
            <td v-else>{{borrow.realReturnDate}}</td>
            <td v-if="borrow.isExceeded=='逾期'"><font color="red">逾&emsp;期</font></td>
            <td v-else>{{borrow.isExceeded}}</td>
        </tr>
        </tbody>
    </table>
    <div class="book-total" id="book-total">共有{{allBorrowCount}}条借阅记录</div>
    <!--分页功能-->
    <div class="pageNav">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" id="page" href="#" @click="pageChange(prePage)" v-if="flag==true">上一页</a> </li>
            <li class="page-item"><a class="page-link" id="page" href="#" @click="searchPage(prePage)" v-if="flag==false">上一页</a> </li>
            <li class="page-item active" v-for="pages in pageNum" v-if="pages==page">
                <a class="page-link" id="page" @click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a>
                <a class='page-link' id='page' @click="searchPage(pages,search)" href="#" v-else>{{pages}}</a>
            </li>
            <li class='page-item' v-else>
                <a class='page-link' id='page' @click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a>
                <a class='page-link' id='page' @click="searchPage(pages,search)" href="#" v-else>{{pages}}</a>
            </li>

            <li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(nextPage)" v-if="flag == true">下一页</a></li>
            <li class='page-item'><a class='page-link' id='page' href="#" @click="searchPage(nextPage,search)" v-if="flag == false">下一页</a></li>
        </ul>
    </div>
</div>

</body>
</html>
