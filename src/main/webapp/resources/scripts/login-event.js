$(function(){
	// 随机数，确定当前页面的验证码
	var checkCodeFlag = Math.random();
	//页面加载后加载验证码
	$("#loginCodeImage").attr("src", "user/codeImage.action?random="+ checkCodeFlag +"&test=" + Math.random());
	
	$("#loginCodeImage").click(function(){
		$("#loginCodeImage").attr("src", "user/codeImage.action?random="+ checkCodeFlag +"&test=" + Math.random());
	});
	
	//鼠标移如输入框清除错误样式
	$("#user-info").focusin(function() {
		$("#loginMessage").hide();
	});
	//点击登录按钮 /login.action
	$("#login").click(function(){
		var userName = $("#userName").val().trim();
		var pwd = $("#pwd").val().trim();
		var checkCode = $("#inputCheckCode").val().trim().toLowerCase();
		if(!checkCheckCode(checkCode)) {
			$("#loginMessage").show();
			$("#errorMessage").text("验证码错误!!");
			return;
		} 
		if(((!checkNickname(userName))&&(!checkEmail(userName))) || (!checkPassword(pwd))) {
			$("#loginMessage").show();
			$("#errorMessage").text("用户名或密码错误");
			return;
		}
		//如果使用邮箱登录则转换为小写
		if(checkEmail(userName)) {
			userName = userName.toLowerCase();
		}
		//发送请求
		$.ajax({
			data:{"userName":userName,"password":pwd,"checkCode":checkCode+"&" + checkCodeFlag},
			dataType:"json",
			error:function() {
				$("#loginMessage").show();
				$("#errorMessage").text("连接服务器失败");
			},
			success:function(result) {
				if(result.status === 1) {
					var nextURL = window.atob(window.location.search.substr(1));
					//将登录名放入cookie
					$.cookie('userName', userName, {expires:1, path:'/'});
//					//将登录名放入cookie
//					$.cookie('userName', userName, {expires:1, path:'/login/'});
					if("" === nextURL.trim()) {
						window.location.href="index.html";
					} else {
						window.location.href=nextURL;
					}
					return;
				}
				//验证码错误切换验证码
				$("#loginCodeImage").get(0).click();
				$("#loginMessage").show();
				$("#errorMessage").text(result.info);
			},
			type:"post",
			url:"user/login.action"
		});
	});
});