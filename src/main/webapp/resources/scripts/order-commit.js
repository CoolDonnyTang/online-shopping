function loadAddr(){
	//发送请求加载用户地址
	$.ajax({
		data : "",
		dataType : "json",
		error : function() {
		
		},
		success : function(result) {
			if (result.status === 1) {
				var data = result.data;
				var $li;
				//清空列表
				$("#addrList li").remove();
				if(data===undefined || data===null || data.length<1) {
					$("#addrList").append($("<li>您还没有收货地址 赶紧添加一个吧^_^</li>"));
					return;
				}
				for(var i=0; i<data.length; i++) {
					if(i === 0) {
						$li = $("<li><input type='radio' name='addr-list' class='addr-list-c' checked='checked'>"+data[i].recipient+"&nbsp;"+data[i].address+"&nbsp;"+data[i].mobileNumber+"&nbsp;"+"</li>");
					} else {
						$li = $("<li><input type='radio' name='addr-list' class='addr-list-c'>"+data[i].recipient+"&nbsp;"+data[i].address+"&nbsp;"+data[i].mobileNumber+"&nbsp;"+"</li>");
					}
					$li.data("addrId",data[i].id);
					$("#addrList").append($li);
				}
			}
		},
		type : "post",
		url : "../checkLogin/getShippingAddr.action"
	});
}