<%--
  Created by IntelliJ IDEA.
  User: a9573
  Date: 2020/11/14
  Time: 1:51
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
            src="${pageContext.request.contextPath }/resources/js/admin/BookManagement.js"></script>
</head>
<body>
    <div class = "book-top">
        <div class = "book-title">
            <h3>图书信息管理</h3>
        </div>
        <div class = "book_functionArea">
            <div class="book-search input-group-lg">
                <input type="text" class = "book-search-input" placeholder="请输入图书名称">
                <button class = "btn btn-secondary book-search-button"
                        id="book-search-button" @click="searchBook()">搜索</button>
                <button class="btn btn-primary book-add"
                        data-toggle="modal" data-target="#book-add" id="book-add-button">添加书籍</button>
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
                    <td><button class="btn btn-primary book-alert" v-bind:value='index' data-toggle="modal" data-target="#book-alert" id="book-alert-button">修改</button>
                        <button class="btn btn-danger book-delete" v-bind:value="index" id="book-delete-button">删除</button>
                    </td>
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

    <!-- 书籍添加模态框 -->
    <div class="modal fade" id="book-add">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">添加书籍</h4>
                    <button type="button" class="close" id="close-button" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    <form action="" id="book-add-form" method="post">
                        <div>
                            <label for="book-id">书籍编号:</label>
                            <input type="text" name="id" id="book-id">
                        </div>
                        <div>
                            <label for="book-name">书籍名称:</label>
                            <input type="text" name="name" id="book-name">
                        </div>
                        <div>
                            <label for="book-author">作者:</label>
                            <input type="text" name="author" id="book-author">
                        </div>
                        <div>
                            <label for="book-type">所属种类:</label>
                                <select id="book-type" name="type">
                                    <option value="">---请选择---</option>
                                    <option value="A 马列主义、毛泽东思想、邓小平理论">A 马列主义、毛泽东思想、邓小平理论</option>
                                    <option value="B 哲学、宗教">B 哲学、宗教</option>
                                    <option value="C 社会科学总论">C 社会科学总论</option>
                                    <option value="D 政治、法律">D 政治、法律</option>
                                    <option value="E 军事">E 军事</option>
                                    <option value="F 经济">F 经济</option>
                                    <option value="G 文化、科学、教育、体育">G 文化、科学、教育、体育</option>
                                    <option value="H 语言、文字">H 语言、文字</option>
                                    <option value="I 文学">I 文学</option>
                                    <option value="J 艺术">J 艺术</option>
                                    <option value="K 历史、地理">K 历史、地理</option>
                                    <option value="N 自然科学总论">N 自然科学总论</option>
                                    <option value="O 数理科学与化学">O 数理科学与化学</option>
                                    <option value="P 天文学、地球科学">P 天文学、地球科学</option>
                                    <option value="Q 生物科学">Q 生物科学</option>
                                    <option value="R 医药、卫生">R 医药、卫生</option>
                                    <option value="S 农业科学">S 农业科学</option>
                                    <option value="T 工业技术">T 工业技术</option>
                                    <option value="U 交通运输">U 交通运输</option>
                                    <option value="V 航空、航天">V 航空、航天</option>
                                    <option value="X 环境科学,安全科学">X 环境科学,安全科学</option>
                                    <option value="Z 综合性图书">Z 综合性图书</option>
                                </select>
                        </div>
                        <div>
                            <label for="book-press">出版社:</label>
                            <input type="text" name="press" id="book-press">
                        </div>
                        <div>
                            <label for="book-total-num">总量:</label>
                            <input type="text" name="totalNum" id="book-total-num">
                        </div>
                        <div>
                            <label for="book-price">价格:</label>
                            <input type="text" name="price" id="book-price">
                        </div>
                    </form>
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button class="btn btn-primary" id="book-add-input">提交</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 书籍修改模态框 -->
    <div class="modal fade" id="book-alert">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">添加书籍</h4>
                    <button type="button" class="close" id="close-button" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    <form action="" id="book-alert-form" method="post">
                        <div>
                            <label for="book-id">书籍编号:</label>
                            <input type="text" name="id" id="book-id" readonly="readonly">
                        </div>
                        <div>
                            <label for="book-name">书籍名称:</label>
                            <input type="text" name="name" id="book-name">
                        </div>
                        <div>
                            <label for="book-author">作者:</label>
                            <input type="text" name="author" id="book-author">
                        </div>
                        <div>
                            <label for="book-type">所属种类:</label>
                            <select id="book-type" name="type">
                                <option value="">---请选择---</option>
                                <option value="A 马列主义、毛泽东思想、邓小平理论">A 马列主义、毛泽东思想、邓小平理论</option>
                                <option value="B 哲学、宗教">B 哲学、宗教</option>
                                <option value="C 社会科学总论">C 社会科学总论</option>
                                <option value="D 政治、法律">D 政治、法律</option>
                                <option value="E 军事">E 军事</option>
                                <option value="F 经济">F 经济</option>
                                <option value="G 文化、科学、教育、体育">G 文化、科学、教育、体育</option>
                                <option value="H 语言、文字">H 语言、文字</option>
                                <option value="I 文学">I 文学</option>
                                <option value="J 艺术">J 艺术</option>
                                <option value="K 历史、地理">K 历史、地理</option>
                                <option value="N 自然科学总论">N 自然科学总论</option>
                                <option value="O 数理科学与化学">O 数理科学与化学</option>
                                <option value="P 天文学、地球科学">P 天文学、地球科学</option>
                                <option value="Q 生物科学">Q 生物科学</option>
                                <option value="R 医药、卫生">R 医药、卫生</option>
                                <option value="S 农业科学">S 农业科学</option>
                                <option value="T 工业技术">T 工业技术</option>
                                <option value="U 交通运输">U 交通运输</option>
                                <option value="V 航空、航天">V 航空、航天</option>
                                <option value="X 环境科学,安全科学">X 环境科学,安全科学</option>
                                <option value="Z 综合性图书">Z 综合性图书</option>
                            </select>
                        </div>
                        <div>
                            <label for="book-press">出版社:</label>
                            <input type="text" name="press" id="book-press">
                        </div>
                        <div>
                            <label for="book-total-num">总量:</label>
                            <input type="text" name="totalNum" id="book-total-num">
                        </div>
                        <div>
                            <label for="book-total-num">余量:</label>
                            <input type="text" name="remainingNum" id="book-remaining-num">
                        </div>
                        <div>
                            <label for="book-price">价格:</label>
                            <input type="text" name="price" id="book-price">
                        </div>
                    </form>
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button class="btn btn-primary" id="book-alert-input">提交</button>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
