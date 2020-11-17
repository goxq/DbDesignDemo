<%--
  Created by IntelliJ IDEA.
  User: a9573
  Date: 2020/11/16
  Time: 15:31
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
          href="${pageContext.request.contextPath }/resources/css/BookManagement.css">

    <!-- 引入JS文件 -->
    <script
            src="${pageContext.request.contextPath }/resources/js/student/StuBookManagement.js"></script>
</head>
<body>
<div class = "book-top">
    <div class = "book-title">
        <h3>图书信息</h3>
    </div>
    <div class = "book_functionArea">
        <div class="book-search input-group-lg">
            <input type="text" class = "book-search-input" placeholder="请输入图书名称">
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
            <th>书籍编号</th>
            <th>书名</th>
            <th>类型</th>
            <th>作者</th>
            <th>出版社</th>
            <th>总量</th>
            <th>余量</th>
            <th>价格</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(book,index) in bookList">
            <td>{{book.id}}</td>
            <td>{{book.name}}</td>
            <td>{{book.type}}</td>
            <td>{{book.author}}</td>
            <td>{{book.press}}</td>
            <td>{{book.totalNum}}</td>
            <td>{{book.remainingNum}}</td>
            <td>{{book.price}}</td>
            <td><button class="btn btn-primary book-borrow"  data-toggle="modal" data-target="#book-borrow" v-bind:value="index" id="book-borrow-button">借阅</button></td>
        </tr>
        </tbody>
    </table>
    <div class="book-total" id="book-total">共有{{allBookCount}}本书</div>
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


<!-- 书籍借阅模态框 -->
<div class="modal fade" id="book-borrow">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">借阅书籍</h4>
                <button type="button" class="close" id="close-button" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form action="" id="book-borrow-form" method="post">
                    <div>
                        <label for="stu-id">学号:</label>
                        <input type="text" name="stuId" id="stu-id" readonly="readonly">
                    </div>
                    <div>
                        <label for="book-id">书籍编号:</label>
                        <input type="text" name="bookId" id="book-id" readonly="readonly">
                    </div>
                    <div>
                        <label for="book-name">书籍名称:</label>
                        <input type="text" name="bookName" id="book-name"  readonly="readonly">
                    </div>
                    <div>
                        <label for="book-author">作者:</label>
                        <input type="text" name="author" id="book-author" readonly="readonly">
                    </div>
                    <div>
                        <label for="expect-return-date">还书日期:</label>
                        <input type="date" name="expectReturnDate" id="expect-return-date">
                    </div>
                </form>
            </div>
            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button class="btn btn-primary" id="book-borrow-input">提交</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
