function disUserName() {
	var user = $.cookie('userName');
	console.log(user);
	if(undefined !== user && null !== user && 'null' !== user) {
		$("#userAndLogExit").show();
		$("#userName").text(user);
		$("#loginAndRegister").hide();
	}
}
//给退出按钮注册点击事件
$("#userAndLogExit a:contains(退出)").click(function(){
	//发送ajax
	$.ajax({
		data:"",
		dataType:"json",
		error:function() {
			//$("#mainKinds").text("列表加载失败");
			console.log("333");
		},
		success:function(result) {
			console.log(result);
			if(result.status === 1) {
				//清除用户名cookie
				$.cookie('userName', null, { expires:0, path:'/' }); 
				//刷新当前页面
				location.reload(true);
			}
		},
		type:"get",
		url:"/user/logout.action",
	});
});
disUserName();

