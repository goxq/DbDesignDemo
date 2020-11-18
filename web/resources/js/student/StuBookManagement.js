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
 * 点击借阅书籍事件，设置表单中的数据
 */
$("#table-content").on("click","#book-borrow-button",function () {
    var stu_id = $("#user").val();
    var rowIndex = $(this).val();
    var j = Number(rowIndex) + 1;
    $("#book-borrow-form div #stu-id").val(stu_id);
    $("#book-borrow-form div #book-id").val($("#table-content tr:eq("+(j)+") td:eq(0)").text());
    $("#book-borrow-form div #book-name").val($("#table-content tr:eq("+(j)+") td:eq(1)").text());
    $("#book-borrow-form div #book-author").val($("#table-content tr:eq("+(j)+") td:eq(3)").text());
})


/*
 * 借阅书籍
 * 表单校验
 */
$("#book-borrow-form").validator({
    rules : {
        //自定义规则
        idValidate : [ /^\d{10}$/, "书籍编号长度必须为10位数字" ],
    },
    fields : {
        'stuId' : "required",
        'bookId' : "required;idValidate",
        'expectReturnDate' : "required;dateValidate"

    },
    valid : function (form) {
        $.ajax({
            type : "POST",
            url : "BorrowBookServlet",
            data : $(form).serialize(),
            success : function (result) {
                alert("借阅成功");
                $("#book-borrow").modal("hide");
                $(".modal-backdrop").remove();
                $("#contain").load($("#path").val() + "/StuBookManagementUIServlet");
            },
            error : function (e) {
                if(e.status === 401){
                    alert(e.responseText);
                }else{
                    alert("未知错误 "+e.status+",请刷新重试");
                }
            }
        });
    }
});





/*
 * 模态框关闭后刷新界面
 */
$("#close-button").click(function () {
    $("book-add").modal("hide");
    $(".modal-backdrop").remove();
    $("#contain").load($("#path").val() + "/StuBookManagementUIServlet");
});


