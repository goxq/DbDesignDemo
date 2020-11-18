var vueBorrowContent = new Vue({
    el : '#contain',
    data : {
        borrowList : [],
        allBorrowCount : "",
        prePage : "",
        nextPage : "",
        pageNum : [],
        page : "",
        search : "",
        flag : true,//flag为true时表示不是搜索，为false时表示为搜索
    },
    mounted : function () {//初始化执行
        $.get("BorrowManagementServlet?page=1", function (data,status) {
            vueBorrowContent.borrowList = data.borrowList;
            vueBorrowContent.page=data.page;
            vueBorrowContent.prePage=data.prePage;
            vueBorrowContent.nextPage=data.nextPage;
            vueBorrowContent.pageNum=data.pageNum;
            vueBorrowContent.allBorrowCount=data.allBorrowCount;
            vueBorrowContent.search="";
            vueBorrowContent.flag=true;
        })
    },
    methods : {
        pageChange : function (page) {
            this.$nextTick(function() {
                $.get("BorrowManagementServlet?page=" + page, function(data, status) {
                    vueBorrowContent.borrowList = data.borrowList;
                    vueBorrowContent.page=data.page;
                    vueBorrowContent.prePage=data.prePage;
                    vueBorrowContent.nextPage=data.nextPage;
                    vueBorrowContent.pageNum=data.pageNum;
                    vueBorrowContent.allBorrowCount=data.allBorrowCount;
                    vueBorrowContent.search="";
                    vueBorrowContent.flag=true;
                });
            });
        },
        searchBook : function () {
            this.$nextTick(function(){
                $.get("SearchBorrowServlet?page=1&search="+$(".borrow-search-input").val(),function(data,status){
                    vueBorrowContent.borrowList = data.borrowList;
                    vueBorrowContent.page=data.page;
                    vueBorrowContent.prePage=data.prePage;
                    vueBorrowContent.nextPage=data.nextPage;
                    vueBorrowContent.pageNum=data.pageNum;
                    vueBorrowContent.allBorrowCount=data.allBorrowCount;
                    vueBorrowContent.search="";
                    vueBorrowContent.flag=true;
                })
            })
        },
        searchPage : function (page,search) {
            this.$nextTick(function(){
                $.get("SearchBorrowServlet?page="+page+"&search="+search,function(data,status){
                    vueBorrowContent.borrowList = data.borrowList;
                    vueBorrowContent.page=data.page;
                    vueBorrowContent.prePage=data.prePage;
                    vueBorrowContent.nextPage=data.nextPage;
                    vueBorrowContent.pageNum=data.pageNum;
                    vueBorrowContent.allBorrowCount=data.allBorrowCount;
                    vueBorrowContent.search="";
                    vueBorrowContent.flag=true;
                })
            })
        }
    }
});
/**
 * 点击确认归还事件，拉取表单内容
 * 把row的index拼接在了“修改”按钮的button里，前十位为bookId，后面的是index。
 */
$("#table-content").on("click","#book-return-button",function () {
    var rowIndex = $(this).val();
    var j = Number(rowIndex) + 1;
    $("#book-return-form div #stu-id").val($("#table-content tr:eq("+(j)+") td:eq(0)").text());
    $("#book-return-form div #book-id").val($("#table-content tr:eq("+(j)+") td:eq(1)").text());
    $("#book-return-form div #book-name").val($("#table-content tr:eq("+(j)+") td:eq(3)").text());
})

/*
 * 确认归还书籍
 * 表单校验
 */
$("#book-return-form").validator({
    rules : {
        //自定义规则

    },
    fields : {
        //表单应用规则
        'stuId' : "required",
        'bookId' : "required;authorValidate",
        'realReturnDate' : "required"
    },
    valid :  function (form) {
        $.ajax({
            type : "POST",
            url : "ReturnBookServlet",
            data : $(form).serialize(),
            success : function (result) {
                alert("确认归还成功！");
                $("#book-return").modal("hide");
                $(".modal-backdrop").remove();
                $("#contain").load($("#path").val() + "/BorrowManagementUIServlet");
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



