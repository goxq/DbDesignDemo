var vueStuReturnContent = new Vue({
    el : '#contain',
    data : {
        returnList : [],
        allReturnCount : "",
        prePage : "",
        nextPage : "",
        pageNum : [],
        page : "",
        search : "",
        flag : true,//flag为true时表示不是搜索，为false时表示为搜索
    },
    mounted : function () {//初始化执行
        $.get("StuReturnManagementServlet?page=1", function (data,status) {
            vueStuReturnContent.returnList = data.returnList;
            vueStuReturnContent.page=data.page;
            vueStuReturnContent.prePage=data.prePage;
            vueStuReturnContent.nextPage=data.nextPage;
            vueStuReturnContent.pageNum=data.pageNum;
            vueStuReturnContent.allReturnCount=data.allReturnCount;
            vueStuReturnContent.search="";
            vueStuReturnContent.flag=true;
        })
    },
    methods : {
        pageChange : function (page) {
            this.$nextTick(function() {
                $.get("StuReturnManagementServlet?page=" + page, function(data, status) {
                    vueStuReturnContent.returnList = data.returnList;
                    vueStuReturnContent.page=data.page;
                    vueStuReturnContent.prePage=data.prePage;
                    vueStuReturnContent.nextPage=data.nextPage;
                    vueStuReturnContent.pageNum=data.pageNum;
                    vueStuReturnContent.allReturnCount=data.allReturnCount;
                    vueStuReturnContent.search="";
                    vueStuReturnContent.flag=true;
                });
            });
        },
        searchReturn : function () {
            this.$nextTick(function(){
                $.get("StuSearchReturnServlet?page=1&search="+$(".return-search-input").val(),function(data,status){
                    vueStuReturnContent.returnList = data.returnList;
                    vueStuReturnContent.page=data.page;
                    vueStuReturnContent.prePage=data.prePage;
                    vueStuReturnContent.nextPage=data.nextPage;
                    vueStuReturnContent.pageNum=data.pageNum;
                    vueStuReturnContent.allReturnCount=data.allReturnCount;
                    vueStuReturnContent.search="";
                    vueStuReturnContent.flag=false;
                })
            })
        },
        searchPage : function (page,search) {
            this.$nextTick(function(){
                $.get("StuSearchReturnServlet?page="+page+"&search="+search,function(data,status){
                    vueStuReturnContent.returnList = data.returnList;
                    vueStuReturnContent.page=data.page;
                    vueStuReturnContent.prePage=data.prePage;
                    vueStuReturnContent.nextPage=data.nextPage;
                    vueStuReturnContent.pageNum=data.pageNum;
                    vueStuReturnContent.allReturnCount=data.allReturnCount;
                    vueStuReturnContent.search="";
                    vueStuReturnContent.flag=false;
                })
            })
        }
    }
});
