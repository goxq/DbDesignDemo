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



