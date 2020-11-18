var vueStuBorrowContent = new Vue({
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
        $.get("StuBorrowManagementServlet?page=1", function (data,status) {
            vueStuBorrowContent.borrowList = data.borrowList;
            vueStuBorrowContent.page=data.page;
            vueStuBorrowContent.prePage=data.prePage;
            vueStuBorrowContent.nextPage=data.nextPage;
            vueStuBorrowContent.pageNum=data.pageNum;
            vueStuBorrowContent.allBorrowCount=data.allBorrowCount;
            vueStuBorrowContent.search="";
            vueStuBorrowContent.flag=true;
        })
    },
    methods : {
        pageChange : function (page) {
            this.$nextTick(function() {
                $.get("StuBorrowManagementServlet?page=" + page, function(data, status) {
                    vueStuBorrowContent.borrowList = data.borrowList;
                    vueStuBorrowContent.page=data.page;
                    vueStuBorrowContent.prePage=data.prePage;
                    vueStuBorrowContent.nextPage=data.nextPage;
                    vueStuBorrowContent.pageNum=data.pageNum;
                    vueStuBorrowContent.allBorrowCount=data.allBorrowCount;
                    vueStuBorrowContent.search="";
                    vueStuBorrowContent.flag=true;
                });
            });
        },
        searchBook : function () {
            this.$nextTick(function(){
                $.get("StuSearchBorrowServlet?page=1&search="+$(".borrow-search-input").val(),function(data,status){
                    vueStuBorrowContent.borrowList = data.borrowList;
                    vueStuBorrowContent.page=data.page;
                    vueStuBorrowContent.prePage=data.prePage;
                    vueStuBorrowContent.nextPage=data.nextPage;
                    vueStuBorrowContent.pageNum=data.pageNum;
                    vueStuBorrowContent.allBorrowCount=data.allBorrowCount;
                    vueStuBorrowContent.search="";
                    vueStuBorrowContent.flag=true;
                })
            })
        },
        searchPage : function (page,search) {
            this.$nextTick(function(){
                $.get("StuSearchBorrowServlet?page="+page+"&search="+search,function(data,status){
                    vueStuBorrowContent.borrowList = data.borrowList;
                    vueStuBorrowContent.page=data.page;
                    vueStuBorrowContent.prePage=data.prePage;
                    vueStuBorrowContent.nextPage=data.nextPage;
                    vueStuBorrowContent.pageNum=data.pageNum;
                    vueStuBorrowContent.allBorrowCount=data.allBorrowCount;
                    vueStuBorrowContent.search="";
                    vueStuBorrowContent.flag=true;
                })
            })
        }
    }
});



