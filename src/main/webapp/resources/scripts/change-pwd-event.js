$("#checkMail").click(function(){
	//检查按钮是否可点击
	if($("#checkMail").attr("disabled")==="disabled") {
		return;
	}
	//清除验证码提示信息
	$("#checkCodeMessage").text("");
	var email = $("#inputEmail").val().trim().toLowerCase();
	$("#checkMailMessage").text("");
	//使”下一步“按钮不可点击
	$("#checkMailAndCode").attr("disabled", "disabled");
	if(!checkEmail(email)) {
		$("#checkMailMessage").text("请输入正确的邮箱格式");
	} else {
		$("#checkMailMessage").text("发送中...");
		$.ajax({
			data:{"email":email,"exist":true},
			dataType:"json",
			type:"post",
			url:"user/checkMail.action",
			error:function() {
				$("#checkMailMessage").text("连接服务器失败");
				//使发送验证码按钮可点击
				$("#checkMail").removeAttr("disabled");
			},
			success:function(result) {
				if(result.status === 1) {
					//重新发送验证码时间
					var resendTime = 10;
					//下一步按钮可点击
					$("#checkMailAndCode").removeAttr("disabled");
					//验证码按钮不可点击
					$("#checkMail").attr("disabled", "disabled");
					$("#checkMailMessage").text(resendTime + "秒后可再次发送");
					var timerId = window.setInterval(function() {
						if(resendTime < 1) {
							$("#checkMailMessage").text("");
							clearInterval(timerId);
							//使发送验证码按钮可点击
							$("#checkMail").removeAttr("disabled");
							return;
						}
						$("#checkMailMessage").text(--resendTime + "秒后可再次发送");
					}, 1000);
				} else {
					$("#checkMailMessage").text(result.info);
					//使发送验证码按钮可点击
					$("#checkMail").removeAttr("disabled");
				}
			}
		});
	}
	
//鼠标切入验证码框时清除提示信息
$("#inputCheckCode").focusin(function() {
	$("#checkCodeMessage").text("");
});
//点击下一步时
$("#checkMailAndCode").click(function(){
	var email = $("#inputEmail").val().trim().toLowerCase();
	//验证码值
	var checkCode = $("#inputCheckCode").val().trim().toLowerCase();
	//检查按钮是否可点击且验证码是否为5位
	if($("#checkMailAndCode").attr("disabled")==="disabled") {
		return;
	}
	//清除提示信息
	$("#checkCodeMessage").text("");
	if(checkCode.length != 5) {
		$("#checkCodeMessage").text("请确认验证码是否正确");
		return;
	}
	$.ajax({
			data:{"email":email,"checkCode":checkCode},
			dataType:"json",
			type:"post",
			url:"user/checkEmailCode.action",
			error:function() {
				$("#checkCodeMessage").text("验证失败 请稍后再试");
			},
			success:function(result) {
				if(result.status === 1) {
					$("#input-info").show();
					$("#verify").hide();
					$("#progress").width("66%").text("密码重置 2/3");
				} else {
					//使”下一步“按钮不可点击
					$("#checkMailAndCode").attr("disabled", "disabled");
					$("#checkCodeMessage").text(result.info);
				}
			}
		});
	});
	
	//点击提交按钮提交
	$("#submit-info").click(function(){
		var pwd = $("#inputPwd").val();
		var pwd2 = $("#inputPwd2").val(); 
		//标识验证是否通过
		//var flag = true;
		//隐藏警告框
		$("#warning-message").hide();
		if(!checkPassword(pwd)) {
			$("#pwd1Error").text("* 密码组合不合法");
			$("#pwd1Error").parent().addClass("has-error");
			flag = false;
		}
		if(pwd !== pwd2) {
			$("#pwd2Error").text("* 两次密码输入不相同");
			$("#pwd2Error").parent().addClass("has-error");
			flag = false;
		}
		/* if(flag === false) {
			return;
		} */
		//验证通过发送ajax
		$.ajax({
			data:{"password":pwd},
			dataType:"json",
			error:function() {
				$("#warning-message").show();
				$("#warning-message").text("连接服务器失败，请稍后再试");
			},
			success:function(result) {
				if(result.status === 1) {
					$("#input-info").hide();
					$("#register-result").show();
					$("#progress").width("100%").text("修改成功 3/3");
					$("#resultMessage").text("密码修改成功");
				} else if (result.status===-1 || result.status === -2) {
					$("#warning-message").show();
					$("#warning-message").text(result.info);
				} else { //为0时是邮箱验证失效
					$("#warning-message").show();
					$("#warning-message").text("邮箱验证过期，请刷新页面重试");
				}
			},
			type:"post",
			url:"user/updateUser.action"
		});
	});
	
});