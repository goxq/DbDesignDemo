var vueReturnContent = new Vue({
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
        $.get("ReturnManagementServlet?page=1", function (data,status) {
            vueReturnContent.returnList = data.returnList;
            vueReturnContent.page=data.page;
            vueReturnContent.prePage=data.prePage;
            vueReturnContent.nextPage=data.nextPage;
            vueReturnContent.pageNum=data.pageNum;
            vueReturnContent.allReturnCount=data.allReturnCount;
            vueReturnContent.search="";
            vueReturnContent.flag=true;
        })
    },
    methods : {
        pageChange : function (page) {
            this.$nextTick(function() {
                $.get("ReturnManagementServlet?page=" + page, function(data, status) {
                    vueReturnContent.returnList = data.returnList;
                    vueReturnContent.page=data.page;
                    vueReturnContent.prePage=data.prePage;
                    vueReturnContent.nextPage=data.nextPage;
                    vueReturnContent.pageNum=data.pageNum;
                    vueReturnContent.allReturnCount=data.allReturnCount;
                    vueReturnContent.search="";
                    vueReturnContent.flag=true;
                });
            });
        },
        searchBook : function () {
            this.$nextTick(function(){
                $.get("SearchReturnServlet?page=1&search="+$(".return-search-input").val(),function(data,status){
                    vueReturnContent.returnList = data.returnList;
                    vueReturnContent.page=data.page;
                    vueReturnContent.prePage=data.prePage;
                    vueReturnContent.nextPage=data.nextPage;
                    vueReturnContent.pageNum=data.pageNum;
                    vueReturnContent.allReturnCount=data.allReturnCount;
                    vueReturnContent.search="";
                    vueReturnContent.flag=false;
                })
            })
        },
        searchPage : function (page,search) {
            this.$nextTick(function(){
                $.get("SearchReturnServlet?page="+page+"&search="+search,function(data,status){
                    vueReturnContent.returnList = data.returnList;
                    vueReturnContent.page=data.page;
                    vueReturnContent.prePage=data.prePage;
                    vueReturnContent.nextPage=data.nextPage;
                    vueReturnContent.pageNum=data.pageNum;
                    vueReturnContent.allReturnCount=data.allReturnCount;
                    vueReturnContent.search="";
                    vueReturnContent.flag=false;
                })
            })
        }
    }
});
