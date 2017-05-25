/**
 * 根据订单状态加载订单信息
 * tangyanrentyr
 * 2017年5月11日
 */
function loadOrder(max, orderStatus) {
	console.log("ok");
	//发送请求加载数据
	$.ajax({
		data:{"max":max, "status":orderStatus},
		dataType:"json",
		error:function() {
			
		},
		success:function(result) {
			console.log(result);
			if(result.status === 1) {
				var data = result.data;
				//用于保存每个订单中的所有商品的对象
				var $entity;
				//用于保存每个订单中的对象
				var $order;
				//进行中状态订单添加到进行中订单区域
				if(orderStatus === 1) {
					//清空显示区
					$("#swingOrder").html("");
					for(var i=0; i<data.length; i++) {
						$order = $("<tr>" +
						   				"<td class='js_commEn'>" +
						   				"</td>" +
						   				"<td>" +
						   					"订单号:" + data[i].id + "<br>" +
						   					"<span class='color666'>" + timeToDate(data[i].time) + "</span>" +
						   				"</td>" +
						   				"<td>" +
						   					data[i].name +
						   				"</td>" +
						   				"<td>" +
						   					"<b>￥" + data[i].orderPrice + "</b><br>" +
						   					"<span class='color666'>" + data[i].payment + "</span>" +
						   				"</td>" +
						   				"<td>" +
						   					"<a href='/login/orderDetail.action?"+ window.btoa("orderId="+data[i].id) +"' class='my-link-1'>查看</a>" +
						   				"</td>" +
						   			"</tr>");
						$order.data("id",data[i].id);
						$("#swingOrder").append($order);
						//拼接每个订单的商品信息字符串
						console.log(data[i].entity.length);
						for(var j=0; j<data[i].entity.length; j++) {
							$entity = $("<div class='media cart-commodity'>" +
										  "<div class='media-left media-middle'>" +
										  "<a href=../detail.html?" + 
										     window.btoa("commId=" +data[i].entity[j].commId + "&commEntityId=" + data[i].entity[j].commEntityId)
										     + "><img class='media-object cart-image' src='" + data[i].entity[j].mainUrl +"'></a>" +
										  "</div>" +
										  "<div class='media-body comment'>" +
										    "<p>" + data[i].entity[j].commTitle +"</p>" +
										    "<div class='attribute_'>" +
										    	"<span>(" + data[i].entity[j].commBrand +":" + data[i].entity[j].prop1 + "&nbsp;" + data[i].entity[j].prop2 + ")</span>" +
										    "</div>" +
										  "</div>" +
										"</div>");
							$entity.data("commEntityId", data[i].entity[j].commEntityId);
							$order.find(".js_commEn").append($entity);
						}
					}
				} else if(orderStatus === 2) { //已完成状态订单添加到已完成订单区域
					//清空显示区
					$("#completedOrder").html("");
					for(var i=0; i<data.length; i++) {
						$order = $("<tr>" +
						   				"<td class='js_commEn'>" +
						   				"</td>" +
						   				"<td>" +
						   					"订单号:" + data[i].id + "<br>" +
						   					"<span class='color666'>" + timeToDate(data[i].time) + "</span>" +
						   				"</td>" +
						   				"<td>" +
						   					data[i].name +
						   				"</td>" +
						   				"<td>" +
						   					"<b>￥" + data[i].orderPrice + "</b><br>" +
						   					"<span class='color666'>" + data[i].payment + "</span>" +
						   				"</td>" +
						   				"<td>" +
						   					"<a href='/login/orderDetail.action?"+ window.btoa("orderId="+data[i].id) +"' class='my-link-1'>查看</a>" +
						   				"</td>" +
						   			"</tr>");
						$order.data("id",data[i].id);
						$("#completedOrder").append($order);
						//拼接每个订单的商品信息字符串
						for(var j=0; j<data[i].entity.length; j++) {
							$entity = $("<div class='media cart-commodity'>" +
										  "<div class='media-left media-middle'>" +
										  "<a href=../detail.html?" + 
										     window.btoa("commId=" +data[i].entity[j].commId + "&commEntityId=" + data[i].entity[j].commEntityId)
										     + "><img class='media-object cart-image' src='" + data[i].entity[j].mainUrl +"'></a>" +
										  "</div>" +
										  "<div class='media-body comment'>" +
										    "<p>" + data[i].entity[j].commTitle +"</p>" +
										    "<div class='attribute_'>" +
										    	"<span>(" + data[i].entity[j].commBrand +":" + data[i].entity[j].prop1 + "&nbsp;" + data[i].entity[j].prop2 + ")</span>" +
										    "</div>" +
										  "</div>" +
										"</div>");
							$entity.data("commEntityId", data[i].entity[j].commEntityId);
							$order.find(".js_commEn").append($entity);
						}
					}
				}
			}
		},
		type:"post",
		url:"../checkLogin/queryOrderByOrderStatus.action"
	});
}