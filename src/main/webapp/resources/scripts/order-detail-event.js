$(function(){
	//获取url中的订单号信息
	var orderId = window.atob(window.location.search.replace("?", ""));
	orderId = orderId.substring(orderId.indexOf("=")+1);
	console.log(orderId);
	//发送请求加载订单信息
	$.ajax({
		data : {orderId:orderId},
		dataType : "json",
		error : function() {
		
		},
		success : function(result) {
			if (result.status === 1) {
				//订单基本信息
				var orderBaseInfo = result.data.orderBaseInfo;
				//订单详细
				var orderDetail = result.data.orderDetail;
				/**
				* 处理基本信息
				*/
				//订单状态
				$("#orderStatus").text(orderBaseInfo.orderStatus);
				//收货信息
				$("#shippingMessage").text(orderBaseInfo.address);
				//支付方式
				$("#payment").text(orderBaseInfo.payment);
				//订单金额
				$("#orderPrice").text("￥" + orderBaseInfo.orderPrice);
				/**
				* 处理订单商品列表
				*/
				//清空列表
				$("#orderCommList").html("");
				console.log(orderDetail);
				for(var i=0; i<orderDetail.length; i++) {
					var $trNode = $("<tr>" +
					   				"<td>" +
					   					"<div class='media cart-commodity'>" +
										  "<div class='media-left media-middle'>" +
										  "<a href=../detail.html?" + 
										     window.btoa("commId=" +orderDetail[i].commId + "&commEntityId=" + orderDetail[i].commEntityId)
										     + "><img class='media-object cart-image' src='" + orderDetail[i].mainUrl + "'></a>" +
										  "</div>" +
										  "<div class='media-body comment'>" +
										    "<p>" + orderDetail[i].commTitle + "</p>" +
										    "<div class='attribute_'>" +
										    	"<span>" +orderDetail[i].commBrand + ": " + orderDetail[i].prop1 + " " + orderDetail[i].prop2 + "</span>" +
										    "</div>" +
										 " </div>" +
										"</div>" +
					   				"</td>" +
					   				"<td><b>￥" + orderDetail[i].salePrice + "</b></td>" +
					   				"<td>x" +
					   					orderDetail[i].amount +
					   				"</td>" +
					   				"<td>" +
					   					"<a class='my-link-1'>评价</a>" +
					   				"</td>" +
					   				"<td>" +
					   					"<a class='my-link-1'>售后服务</a>" +
					   				"</td>" +
					   			"</tr>");
					$("#orderCommList").append($trNode);
				}
			} else {
				console.log("failed");
			}
		},
		type : "post",
		url : "../checkLogin/queryOrderMessage.action"
	});
});