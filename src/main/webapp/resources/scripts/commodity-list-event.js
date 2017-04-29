$(function() {
	//获取url后面的参数
	var urlParam = window.decodeURIComponent(window.atob(window.location.search.replace("?", "")));
	var data = {};
	//data[urlParam.substring(0, urlParam.indexOf("=")).trim()] = urlParam.substring(urlParam.indexOf("=") + 1).trim();
console.log(urlParam);

			if(getStringByReg(urlParam, /mainKindId=(\d+)/) !== null) {
		//console.log(getStringByReg(urlParam, /mainKindId=(\d+)/));
		data["mainKindId"] = getStringByReg(urlParam, /mainKindId=(\d+)/);
	} else if(getStringByReg(urlParam, /subKindId=(\d+)/) !== null) {
		//console.log(getStringByReg(urlParam, /subKindId=(\d+)/));
		data["subKindId"] = getStringByReg(urlParam, /subKindId=(\d+)/);
	} else if(getStringByReg(urlParam, /brandId=(\d+)/) !== null) {
		//console.log(getStringByReg(urlParam, /brandId=(\d+)/));
		data["brandId"] = getStringByReg(urlParam, /brandId=(\d+)/);
	} else if(getStringByReg(urlParam, /nameKey=(.+)/) !== null) {
		console.log(getStringByReg(urlParam, /nameKey=(.+)/));
		data["nameKey"] = getStringByReg(urlParam, /nameKey=(.+)/);
	}
	//发送请求加载数据
	$.ajax({
		data:data,
		dataType:"json",
		error:function() {
			alert("连接服务器失败");
		},
		success:function(result) {
			if(result.status === 1) {				
				var data = result.data;
				var $commodity;
				//获取列表容器并清空商品列表
				var $container = $("#commoditiesList").html("");
				if(result.data === null) {
					return;
				}
				for(var i=0; i<data.length; i++) {
					$commodity = $("<div class='col-sm-3 col-md-3 commodities-list' >" + 
									"<div class='thumbnail'>" + 
										"<img src=" + data[i].mainUrl + " class='col-sm-12 col-md-12 list-image'>" + 
										"<div class='caption commodity-dec'>" + 
											"<a class='col-sm-12 col-md-12 header-link commodity-title'>" + data[i].commTitle + "</a>" + 
											"<p class='col-sm-12 col-md-12 commBrand'>(" + data[i].commBrand + ":" + data[i].prop1 + " " + data[i].prop2 + ")</p>" + 
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
				var $images = $("#commoditiesList img");
				for(var j=0; j<$images.length; j++) {
					var iwidth = $images.eq(j).width();
					$images.eq(j).height(iwidth);
				}
			} else {
				//alert(result.info);
			}
		},
		type:"post",
		url:"comm/findComm.action"
	});
	$("#commoditiesList").on("click",".thumbnail",function(event){
		var $target = $(event.target);
		var $container = $target.parents(".commodities-list");
		window.location.href="detail.html?" 
			+ window.btoa("commId=" + $container.data("commId") + "&commEntityId=" +$container.data("commEntityId"));
	});
});