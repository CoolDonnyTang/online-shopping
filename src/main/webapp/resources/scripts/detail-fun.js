/**
 * 根据实体id加载相应的实体信息
 * tangyanrentyr
 * 2017年4月4日
 * @param data
 * @param commEntityId
 * @returns
 */
function chooesEntity(data, commEntityId) {
	var mainImages;
	var commEntity = data.commEntity;
	//清除主图片
	$("#mainImage").attr("src", "#none");
	//清除主图片预选区
	$("#main-img-list").html("");
	//清除参数列表
	$("#params").html("");
	if(null !== commEntity && undefined !== commEntity) {
		for(var i=0; i<commEntity.length; i++) {
			if(commEntity[i].id == commEntityId) {
				var images = commEntity[i].images;
				var param = commEntity[i].params;
				mainImages = new Array(images.length);
				//设置图片
				for(var j=0; j<images.length; j++) {
					mainImages[images[j].serialNumber-1] = $(
							"<div class='thumbnail col-xs-3 main-img-lis'>" +
						      "<img src="+ images[j].url +" id='mainImage'>" +
						    "</div>"
						);
					if(images[j].serialNumber === 1) {
						$("#mainImage").attr("src", images[j].url);
					}
				}
				for(var m=0; m<mainImages.length; m++) {
					$("#main-img-list").append(mainImages[m]);
				}
				//设置参数
				for(var k=0; k<param.length; k++) {
					$("#params").append($(
								"<li role='presentation' class='text-left col-xs-3'>" + param[k].paramContent + "</li>"
							));
				}
				/***选中属性一和二***/
				//遍历属性一
				var prop1btn = $("#prop1Content button");
				for(var pb=0; pb<prop1btn.length; pb++) {
					//每个按钮
					var $btn = prop1btn.eq(pb);
					if($btn.text()===commEntity[i].propty1 || $btn.text()===commEntity[i].propty2) {
						console.log($btn.text());
						$btn.mousedown();
					}
				}
				//遍历属性er
				var prop2btn = $("#prop2Content button");
				for(var pb2=0; pb2<prop2btn.length; pb2++) {
					//每个按钮
					var $btn2 = prop2btn.eq(pb2);
					if($btn2.text()===commEntity[i].propty1 || $btn2.text()===commEntity[i].propty2) {
						console.log($btn2.text());
						$btn2.mousedown();
					}
				}
				/***设置库存价格等信息***/
				//设置库存
				$("#commInventory").text(commEntity[i].inventory);
				//设置市场价
				$("#marketPrice").text(commEntity[i].marketPrice);
				//设置售价
				$("#myPrice").text(commEntity[i].myPrice);
				
			}
		}
	}
	//设置主图片宽等于高
	var $mainImages = $("#main-img-dis img");
	var width;
	for(var n=0; n<$mainImages.length; n++) {
		width = $mainImages.eq(n).width();
		$mainImages.eq(n).height(width);
	}
}
/**
 * 获取当前选中的属性对应的id
 * tangyanrentyr
 * 2017年4月4日
 * @param data
 * @returns
 */
function findEntityId(data) {
	var commEntity = data.commEntity;
	var propContent = "";
	//每个按钮
	var $btn
	//遍历属性一
	var prop1btn = $("#prop1Content button");
	for(var pb=0; pb<prop1btn.length; pb++) {
		$btn = prop1btn.eq(pb);
		if($btn.hasClass("active-btn")) {
			propContent += $btn.text();
		}
	}
	//遍历属性二
	var prop2btn = $("#prop2Content button");
	//先设置属性二全部为不可点
	for(var p2b=0; p2b<prop2btn.length; p2b++) {
		$btn = prop2btn.eq(p2b);
		if($btn.hasClass("active-btn")) {
			propContent += $btn.text();
		}
	}
	//寻找实体
	for(var i=0; i<commEntity.length; i++) {
		if(propContent.indexOf(commEntity[i].propty1)>=0 && propContent.indexOf(commEntity[i].propty2)>=0) {
			return commEntity[i].id;
		}
	}
	return -1;
}
/**
 * 选择显示商品详情or评论or售后
 * tangyanrentyr
 * 2017年4月4日
 * @param data
 */
function chooseMessage(data) {
	if(data === 1) {
		//显示商品详情
		$("#messageDetail").show();
		$("#commentContainer").hide();
		$("#afterSale").hide();
	} else if(data === 2) {
		//显示评论
		$("#commentContainer").show();
		$("#messageDetail").hide();
		$("#afterSale").hide();
	} else if(data === 3) {
		//显示售后
		$("#afterSale").show();
		$("#messageDetail").hide();
		$("#commentContainer").hide();
	}
}
function disMessage(data) {
	$("#disMessage").show();
	$("#disMessage").text(data);
	window.setTimeout(function() {
		$("#disMessage").hide();
	}, 2000);
}

function executeCollect(commEntityId) {
	console.log("1111");
	//发送请求查询当前选中的商品实体是否被收藏
	$.ajax({
		data:{commEntityId:commEntityId},
		dataType:"json",
		error:function(){
			console.log("cuowu");
		},
		success:function(result){
			console.log(result);
			if(result.status === 1) {
				var data = result.data;
				if(data == false) {
					$("#collect").data("collect", data);
					$("#collect").attr("title", "收藏");
					$("#collect").removeClass("glyphicon-star");
					$("#collect").addClass("glyphicon-star-empty");
				} else if(data == true) {
					$("#collect").data("collect", data);
					$("#collect").attr("title", "取消收藏");
					$("#collect").removeClass("glyphicon-star-empty");
					$("#collect").addClass("glyphicon-star");
				}
			}
		},
		type:"post",
		url:"isCollect.action"
	});
}

function operateCollection(commEntityId, nowStatus, pageUrl) {
	//发送请求查询当前选中的商品实体是否被收藏
	$.ajax({
		data:{commEntityId:commEntityId, pageUrl:pageUrl, nowStatus:nowStatus},
		dataType:"json",
		error:function(result){
			console.log("cuowu11111111111");
			console.log(result);
		},
		success:function(result){
			console.log(result);
			if(result.status === 1) {
				var data = result.data;
				if(data == false) {
					$("#collect").data("collect", data);
					$("#collect").attr("title", "收藏");
					$("#collect").removeClass("glyphicon-star");
					$("#collect").addClass("glyphicon-star-empty");
				} else if(data == true) {
					$("#collect").data("collect", data);
					$("#collect").attr("title", "取消收藏");
					$("#collect").removeClass("glyphicon-star-empty");
					$("#collect").addClass("glyphicon-star");
				}
			}
		},
		type:"post",
		url:"checkLogin/collectionOperate.action"
	});
}













