//用于保存分类数据信息
var data;
/**
 * 分类的加载和操作
 * tangyanrentyr
 * 2017年4月21日
 */
function kindLoadAndOperation() {
	//发送请求加载分类
	$.ajax({
		data:"",
		dataType:"json",
		error:function() {
			$("#mainKinds").text("列表加载失败");
		},
		success:function(result) {
			if(result.status === 1) {
				data = result.data;
				//清空列表
				$("#mainKindsList li").remove();
				for(var i=0; i<data.length; i++) {
					var $li = $("<li>" +data[i].kindName + "</li>");
					$li.data("id", data[i].id);
					$("#mainKindsList").append($li);
				}
				$("#mainKindsList li").hover(function(event) {
					var $target = $(event.target);
					var $li = null;
					var subKinds = null;
					$("#activty").hide();
					$("#disSubKinds").show();
					//清空原有列表
					$("#subKindsList li").remove();
					for(var k=0; k<data.length; k++) {
						if(data[k].id === $target.data("id")) {
							subKinds = data[k].subKinds;
							for(var j=0; j<subKinds.length; j++) {
								console.log(subKinds[j].name);
								$li = $("<li class='text-center'>" + subKinds[j].kindName +"</li>");
								$li.data("id",subKinds[j].id);
								$("#subKindsList").append($li);
							}
						}
					}
				});
				$("#mainKindsList li").mouseout(function() {
					$("#disSubKinds").hide();
					$("#activty").show();
				});
				$("#disSubKinds").mouseout(function(event) {
					if(event.target.tagName !== "DIV") {
						return;
					}
					$("#disSubKinds").hide();
					$("#activty").show();
				});
				$("#disSubKinds").mousemove(function() {
					$("#disSubKinds").show();
					$("#activty").hide();
				});
			} else {
				$("#mainKinds").text("列表加载失败");
			}
		},
		type:"post",
		url:"manage/findMain.action",
	});
	//注册主类单击事件
	$("#mainKindsList").on("click","li",function(event) {
		var $target = $(event.target);
		window.location.href="commodity-list.html?" + window.btoa("mainKindId=" + $target.data("id"));
	});
	//注册子类单击事件
	$("#subKindsList").on("click","li",function(event) {
		var $target = $(event.target);
		window.location.href="commodity-list.html?" + window.btoa("subKindId=" + $target.data("id"));
	});
}

/**
 * 加载今日推荐商品列表
 * tangyanrentyr
 * 2017年4月21日
 */
function loadTodayRecommend() {
	$.ajax({
		data:{recommendType:1},
		dataType:"json",
		error:function() {
			//$("#mainKinds").text("列表加载失败");
			console.log("123");
		},
		success:function(result) {
			console.log(result);
			if(result.status === 1) {
				var data = result.data;
				//获取列表容器并清空商品列表
				var $container = $("#recommendList").html("");
				for(var i=0; i<data.length; i++) {
					$commodity = $("<div class='col-sm-2 col-md-2 commodities-list' >" + 
									"<div class='thumbnail'>" + 
										"<img src=" + data[i].mainUrl + " class='col-sm-12 col-md-12 list-image'>" + 
										"<div class='caption commodity-dec'>" + 
											"<a class='col-sm-12 col-md-12 header-link commodity-title'>" + data[i].commTitle + "</a>" + 
											"<p class='col-sm-12 col-md-12 commBrand'>(" + data[i].commBrand + ": " + data[i].prop1 + " " + data[i].prop2 + ")</p>" + 
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
				var $images = $("#recommendList img");
				for(var j=0; j<$images.length; j++) {
					var iwidth = $images.eq(j).width();
					$images.eq(j).height(iwidth);
				}
			}
		},
		type:"post",
		url:"recommend/findRecommend.action",
	});
}

/**
 * 加载经典品牌（即推荐品牌）
 * tangyanrentyr
 * 2017年4月21日
 */
function loadRecommendBrand() {
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
				var data = result.data;
				//获取列表容器并清空商品列表
				var $container = $("#recommendBrand").html("");
				for(var i=0; i<data.length; i++) {
					$commodity = $("<div class='col-sm-4 col-md-4 commodities-list'>" +
										"<div class='thumbnail brandImage'>" +
											"<img src='" + data[i].url + "' style='width:90%'/>" +
										"</div>" +
									"</div>");
					$commodity.data("id",data[i].id);
					$container.append($commodity);
				}
				//获取商品列表下的所有图片
				var $images = $("#recommendBrand img");
				for(var j=0; j<$images.length; j++) {
					var iwidth = $images.eq(j).width();
					$images.eq(j).height(iwidth);
				}
			}
		},
		type:"post",
		url:"recommend/findRecommendBrand.action",
	});
}











