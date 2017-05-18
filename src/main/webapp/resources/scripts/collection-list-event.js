$(function() {
	//发送请求加载数据
	$.ajax({
		data:"",
		dataType:"json",
		error:function() {
			alert("连接服务器失败");
		},
		success:function(result) {
			if(result.status === 1) {				
				var data = result.data;
				var $commodity;
				//获取列表容器并清空商品列表
				var $container = $("#collectionList").html("");
				console.log(result);
				if(result.data === null) {
					return;
				}
				for(var i=0; i<data.length; i++) {
					$commodity = $("<div class='col-xs-3 commodities-list' >" + 
									"<div class='thumbnail'>" + 
										"<img src=" + data[i].mainUrl + " class='col-xs-12 list-image'>" + 
										"<div class='caption commodity-dec'>" + 
											"<a class='col-xs-12 header-link commodity-title'>" + data[i].commTitle + "</a>" + 
											"<p class='col-xs-12 commBrand'>(" + data[i].commBrand + ":" + data[i].prop1 + " " + data[i].prop2 + ")</p>" + 
											"<span class='price-now'>￥" + data[i].price + "</span> " + 
											"<span class='price-original'>￥<s>" + data[i].marketPrice + "</s></span>" + 
										"</div>" + 
									"</div>" + 
								"</div>");
					$commodity.data("commId",data[i].commId);
					$commodity.data("commEntityId",data[i].commEntityId);
					$container.append($commodity);
				}
				//获取商品列表下的所有图片
				var $images = $("#collectionList img");
				for(var j=0; j<$images.length; j++) {
					var iwidth = $images.eq(j).width();
					$images.eq(j).height(iwidth);
				}
			} else {
				//alert(result.info);
			}
		},
		type:"post",
		url:"../checkLogin/queryCollectionEntites.action"
	});
	
	//商品列表点击进入商品详情的点击事件
	$("#collectionList").on("click",".thumbnail",function(event){
		var $target = $(event.target);
		var $container = $target.parents(".commodities-list");
		window.location.href="../detail.html?" 
			+ window.btoa("commId=" + $container.data("commId") + "&commEntityId=" +$container.data("commEntityId"));
	});
});