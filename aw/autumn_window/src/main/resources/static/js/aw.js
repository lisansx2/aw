$(function() {
    $('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var bottomOffset = 60;//页脚高度
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset - bottomOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});


  
var token = $("meta[name='_csrf']").attr("content");  
var header = $("meta[name='_csrf_header']").attr("content");  
//全局的ajax访问，处理ajax清求时sesion超时  
$.ajaxSetup({  
    contentType:"application/x-www-form-urlencoded;charset=utf-8",  
    beforeSend: function (xhr) {
    	 console.log("header:" + header);
    	 console.log("token:" + token);
        if(header && token ){  
            xhr.setRequestHeader(header, token);  
        }  
    },
    complete:function(XMLHttpRequest,textStatus){  
        //通过XMLHttpRequest取得响应头，sessionstatus
        var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");  
        console.log("sessionstatus:" + sessionstatus);
        if(sessionstatus=="timeout"){  
        //如果超时就处理 ，指定要跳转的页面  
            window.location = "[[@{/login}]]";  
        }  
    }  
}); 
