//侧边的菜单事件的执行函数
//Ajax刷新 通过load方法
$(function() {

    $(".nav li a").click(function() {
        var type = $(this).attr("value");
        var address = $(this).attr("href");
        $("a").removeClass("active")
        $(this).addClass("active");
        $('#contain').html("");
        if(type != "logout"){
            $('#contain').load(address);
        }
        switch (type) {
            case 'logout':
                if(confirm("确定退出吗")){
                    window.location.replace(address);
                }
        }
        return false;
    });

});