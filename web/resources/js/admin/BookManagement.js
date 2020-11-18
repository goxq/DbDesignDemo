var vueBookContent = new Vue({
    el : '#contain',
    data : {
        bookList : [],
        allBookCount : "",
        prePage : "",
        nextPage : "",
        pageNum : [],
        page : "",
        search : "",
        flag : true,//flag为true时表示不是搜索，为false时表示为搜索
    },
    mounted : function () {//初始化执行
        $.get("BookManagementServlet?page=1", function (data,status) {
            vueBookContent.bookList = data.bookList;
            vueBookContent.page=data.page;
            vueBookContent.prePage=data.prePage;
            vueBookContent.nextPage=data.nextPage;
            vueBookContent.pageNum=data.pageNum;
            vueBookContent.allBookCount=data.allBookCount;
            vueBookContent.search="";
            vueBookContent.flag=true;
        })
    },
    methods : {
        pageChange : function (page) {
            this.$nextTick(function() {
                $.get("BookManagementServlet?page=" + page, function(data, status) {
                    vueBookContent.bookList = data.bookList;
                    vueBookContent.page = data.page;
                    vueBookContent.prePage = data.prePage;
                    vueBookContent.nextPage = data.nextPage;
                    vueBookContent.pageNum = data.pageNum;
                    vueBookContent.allBookCount = data.allBookCount;
                    vueBookContent.search = "";
                    vueBookContent.flag = true;
                });
            });
        },
        searchBook : function () {
            this.$nextTick(function(){
                $.get("SearchBookServlet?page=1&search="+$(".book-search-input").val(),function(data,status){
                    vueBookContent.bookList = data.bookList;
                    vueBookContent.page = data.page;
                    vueBookContent.prePage = data.prePage;
                    vueBookContent.nextPage = data.nextPage;
                    vueBookContent.pageNum = data.pageNum;
                    vueBookContent.allBookCount = data.allBookCount;
                    vueBookContent.search = data.search;
                    vueBookContent.flag = false;
                })
            })
        },
        searchPage : function (page,search) {
            this.$nextTick(function(){
                $.get("SearchBookServlet?page="+page+"&search="+search,function(data,status){
                    vueBookContent.bookList = data.bookList;
                    vueBookContent.page = data.page;
                    vueBookContent.prePage = data.prePage;
                    vueBookContent.nextPage = data.nextPage;
                    vueBookContent.pageNum = data.pageNum;
                    vueBookContent.allBookCount = data.allBookCount;
                    vueBookContent.search = data.search;
                    vueBookContent.flag = false;
                })
            })
        }
    }
});

/**
 * 点击添加书籍事件，清空表单内容
 */
$("#book-add-button").click(function(){
    $("#book-add-form div #book-id").val("");
    $("#book-add-form div #book-name").val("");
    $("#book-add-form div #book-author").val("");
    $("#book-add-form div #book-press").val("");
    $("#book-add-form div #book-total-num").val("");
    $("#book-add-form div #book-price").val("");
});
/**
 * 点击修改书籍事件，拉取表单内容
 * 把row的index放在了按钮里
 */
$("#table-content").on("click","#book-alert-button",function () {
    var rowIndex = $(this).val();
    var j = Number(rowIndex) + 1;
    $("#book-alert-form div #book-id").val($("#table-content tr:eq("+(j)+") td:eq(0)").text());
    $("#book-alert-form div #book-name").val($("#table-content tr:eq("+(j)+") td:eq(1)").text());
    $("#book-alert-form div #book-type").val($("#table-content tr:eq("+(j)+") td:eq(2)").text());
    $("#book-alert-form div #book-author").val($("#table-content tr:eq("+(j)+") td:eq(3)").text());
    $("#book-alert-form div #book-press").val($("#table-content tr:eq("+(j)+") td:eq(4)").text());
    $("#book-alert-form div #book-total-num").val($("#table-content tr:eq("+(j)+") td:eq(5)").text());
    $("#book-alert-form div #book-remaining-num").val($("#table-content tr:eq("+(j)+") td:eq(6)").text());
    $("#book-alert-form div #book-price").val($("#table-content tr:eq("+(j)+") td:eq(7)").text());
})


/*
 * 添加专业
 * 表单校验
 */
$("#book-add-form").validator({
    rules : {
        //自定义规则
        idValidate : [ /^\d{10}$/, "书籍编号长度必须为10位数字" ],
        authorValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "作家姓名必须是中文或者英文" ],
        priceValidate : [/^(([^0][0-9]+|0)\.([0-9]{1,2})$)|^([^0][0-9]+|0)$/, "输入有误"],//整数或小数
    },
    fields : {
        //表单应用规则
        'id' : "required;idValidate;remote("+$("#path").val()+"/BookIdIsExistServlet)",
        'name' : "required",
        'author' : "required;authorValidate",
        'type' : "required",
        'press' : "required",
        'totalNum' : "required;integer",
        'price' : "required;priceValidate"
    },
    valid : function (form) {
        $.post("AddBookServlet",$(form).serialize(),function (data,status) {
            alert("添加成功！");
            $("#book-add-form div #book-id").val("");
            $("#book-add-form div #book-name").val("");
            $("#book-add-form div #book-author").val("");
            $("#book-add-form div #book-type").val("");
            $("#book-add-form div #book-press").val("");
            $("#book-add-form div #book-total-num").val("");
            $("#book-add-form div #book-price").val("");
        });
    }
});

/*
 * 修改书籍
 * 表单校验
 */
$("#book-alert-form").validator({
    rules : {
        //自定义规则
        authorValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "作家姓名必须是中文或者英文" ],
        priceValidate : [/^[1-9][0-9]*([\.][0-9]{1,2})?$/, "输入有误"],//整数或小数
    },
    fields : {
        //表单应用规则
        'name' : "required",
        'author' : "required;authorValidate",
        'type' : "required",
        'press' : "required",
        'totalNum' : "required;integer",
        'price' : "required;priceValidate"
    },
    valid :  function (form) {
        $.post("AlertBookServlet",$(form).serialize(),function (data,status) {
            alert("修改成功！");
            $("#book-alert").modal("hide");
            $(".modal-backdrop").remove();
            $("#contain").load($("#path").val() + "/BookManagementUIServlet");
        });
    }
});

/*
 * 删除按钮点下后
 */
$("#table-content").on("click","#book-delete-button",function () {
    var rowIndex = $(this).val();
    var j = Number(rowIndex) + 1;
    var bookId = $("#table-content tr:eq("+(j)+") td:eq(0)").text();
    var path = $("#path").val();
    var url = path + "/DeleteBookServlet?id=" + bookId;
    if(confirm("确定删除吗？")){
        $.get(url,function(data,status){
            alert("删除成功!");
            $('#contain').load(path + "/BookManagementUIServlet");
        });
    }
})

/*
 * 模态框关闭后刷新界面
 */
$("#close-button").click(function () {
    $("book-add").modal("hide");
    $(".modal-backdrop").remove();
    $("#contain").load($("#path").val() + "/BookManagementUIServlet");
});

