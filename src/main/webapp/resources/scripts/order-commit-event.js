$(function() {
	//获取当前url参数
	var param = window.atob(window.location.search.replace("?", ""));
	var parmArray = param.split("&");
	var commEntitiesId = new Array(parmArray.length);
	for (var j = 0; j < parmArray.length; j++) {
		commEntitiesId[j] = parseInt(parmArray[j].split("|")[0]);
	}
	
	//加载收货地址
	loadAddr();
	
	//发送请求加载商品数据
	$.ajax({
		data : {commEntitiesId:commEntitiesId},
		dataType : "json",
		error : function() {

		},
		success : function(result) {
			if (result.status === 1) {
				var data = result.data;
				//清空列表
				$("#commEnList tr").remove();
				//总价
				var totalPrice = 0;
				for (var i = 0; i < data.length; i++) {
					var amount;
					for (var k = 0; k < parmArray.length; k++) {
						if (data[i].commEntityId === parseInt(parmArray[k]
								.split("|")[0])) {
							amount = parmArray[k].split("|")[1];
						}
					}
					//console.log(result);
					var $trNode = $("<tr>"
							+ "<td>"
							+ "<div class='media cart-commodity'>"
							+ "<div class='media-left media-middle'>"
							+ "<a href=../detail.html?" 
							+ window.btoa("commId=" +data[i].commId + "&commEntityId=" + data[i].commEntityId)
						    + "><img class='media-object cart-image' src='"+ data[i].mainUrl +"'></a>"
							+ "</div>"
							+ "<div class='media-body comment'>"
							+ "<p><b>"
							+ data[i].commTitle
							+ "</b></p>"
							+ "<div class='attribute_'>"
							+ "<span>"
							+ data[i].commBrand
							+ ": "
							+ data[i].prop1
							+ data[i].prop2
							+ "</span>"
							+ "</div>"
							+ "</div>"
							+ "</div>"
							+ "</td>"
							+ "<td><b>￥"
							+ data[i].price
							+ "</b></td>"
							+ "<td>x"
							+ amount
							+ "</td>"
							+ "<td>库存 "
							+ data[i].inventory
							+ "</td>" + "</tr>");
					totalPrice = totalPrice + data[i].price * amount;
					$trNode.data("commEntityId", data[i].commEntityId);
					$trNode.data("amount", amount);
					$("#commEnList").append($trNode);
				}
				$("#totalPrice").text("￥"+totalPrice);
			}
		},
		type : "post",
		url : "../checkLogin/findCommEntitiesById.action"
	});
	//新建地址按钮点击事件
	$("#addNewAddr").click(function(){
		//判断当前是否已有添加区,是隐藏的则显示
		if($("#addrMessage").is(':hidden')) {
			$("#addrList").hide();
			$("#addrMessage").show();
			//设置按钮为"取消"
			$("#addNewAddr").text('取消添加');
			//显示保存按钮
			$("#commitAddr").show();
		} else {
			$("#addrList").show();
			$("#addrMessage").hide();
			//设置按钮为"添加新地址"
			$("#addNewAddr").text('添加新地址');
			//隐藏保存按钮
			$("#commitAddr").hide();
		}
	});
	//保存新建地址'保存'按钮事件
	$("#commitAddr").click(function(){
		var data = new FormData($("#addrForm").get(0));
		//发送请求保存数据
		$.ajax({
			data : data,
			dataType : "json",
			cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
			error : function() {
			
			},
			success : function(result) {
				if (result.status === 1) {
					$("#addrList").show();
					$("#addrMessage").hide();
					//隐藏保存按钮
					$("#commitAddr").hide();
					//设置按钮为"添加新地址"
					$("#addNewAddr").text('添加新地址');
					//加载收货地址
					loadAddr();
				} else {
					console.log("failed");
				}
			},
			type : "post",
			url : "../checkLogin/addShippingAddr.action"
		});
	});
	//点击返回购物车
	$("#gotoShoppingCart").click(function(){
		window.location.href = "../login/shopping-cart.action"; 
	});
	
	//点击提交按钮提交订单
	$("#commitOrder").click(function(){
		//获取地址id
		var addrId = $("#addrList input:checked").parents("li").data("addrId");
		var entityIdAndAmount = [];
		//所有商品tr
		var $tr = $("#commEnList tr");
		//支付方式
		var payment = $("#payment input:checked").val();
		for(var i=0; i<$tr.length; i++) {
			entityIdAndAmount.push("commEntityId="+$tr.eq(i).data("commEntityId") + "&amount=" +$tr.eq(i).data("amount"))
		}
		console.log(entityIdAndAmount);
		//发送请求提交订单数据
		if(payment == 1) {
			alert("该付款方式仍在开发中...");
			return;
		}
		$.ajax({
			data : {addrId:addrId, entityIdAndAmount:entityIdAndAmount,payment:payment},
			dataType : "json",
			error : function() {
				
			},
			success : function(result) {
				if (result.status === 1) {
					console.log(result);
					var data = result.data;
					var orderIdParam = window.btoa("orderId="+data.id);
					if(data.payment === "货到付款") {
						window.location.href = "../login/orderDetail.action?"+orderIdParam;
					}
				} else if(result.status === 0) {
					$("#disMessage").show();
					$("#disMessage").text(result.info);
				}
			},
			type : "post",
			url : "../checkLogin/commitOrder.action"
		});
	});
	
	
	
});