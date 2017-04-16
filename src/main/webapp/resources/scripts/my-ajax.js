/**
 * 重写全局ajax方法，解决登录失效不跳转问题
 */
jQuery(function($){
    // 备份jquery的ajax方法  
    var _ajax=$.ajax;
    // 重写ajax方法，
    $.ajax=function(opt){
        //var _success = opt && opt.success || function(a, b){};
        var _error = opt && opt.error || function(a, b){};
        var _opt = $.extend(opt, {
        	/*success:function(data, textStatus){
				console.log(data);
				// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要判断(登录页面一般是源码，所以这里只判断是否有html标签)
			    if(data.info == undefined) {
			        window.location.href= "login.html";
			        return;
			    }
			    _success(data, textStatus);  
			},*/
			error:function(data, textStatus){
				//alert(data.responseText);
				var data1 = data.responseText.trim();
				if(data1.indexOf("href")===0){
					window.location.href=data1.substring(data1.indexOf("=")+1);
			    }
				_error(data, textStatus);
			} 
        });
        return _ajax(_opt);
    };
});