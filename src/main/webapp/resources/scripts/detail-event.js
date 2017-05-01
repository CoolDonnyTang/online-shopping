$(function(){
			var commData;
			var param =  window.atob(window.location.search.replace("?", ""));
	var data = {};
	data[param.substr(0, param.indexOf("=")).trim()] = param.substr(param.indexOf("=") + 1, param.indexOf("&")-param.indexOf("=")-1).trim();
	var commEntityId = parseInt(param.substr(param.lastIndexOf("=") + 1).trim());
	console.log(data);
	//发送请求加载数据
	$.ajax({
		data:data,
		dataType:"json",
		error:function(){
		
		},
		success:function(result){
			if(result.status === 1 && result.data !== null) {
				var data = result.data;
				commData = data;
				var selectEntity = null;
				//找到应该选中的实体的信息
				for(var i=0; i<data.commEntity.length; i++) {
					if(data.commEntity[i].id == commEntityId) {
						selectEntity = data.commEntity[i];
					}
				}
				/**
				*加载基本信息
				*/
				//加载标题
				$("#commTitle").text(data.titleName);
				//加载品牌
				$("#brand").text(data.brand);
				//加载详情图片
				$("#detailImage").html("");
				var detailImages = new Array(data.images.length);
				for(var j=0; j<data.images.length; j++) {
					detailImages[data.images[j].serialNumber-1] = $("<li><img src=" + data.images[j].url + " class='col-md-12 image-display'></li>");
				}
				for(var k=0; k<detailImages.length; k++) {
					$("#detailImage").append(detailImages[k]);
				}
				//加载属性
				var props = data.properties;
				if(props.length === 1) {
					//移除属性二
					$("#prop2-con").html("");
					//移除属性的选项
					$("#prop1Content").html("");
					$("#prop1Name").text(props[0].propertyName + ":");
					var propContent = props[0].propertyCotent.split(/\s+/);
					for(var propc=0; propc<propContent.length; propc++) {
						$("#prop1Content").append($(
								"<button type='button' class='btn btn-default prop'>" + propContent[propc] + "</button>"
							));
					}
				} else if(props.length === 2) {
					if(props[0].id<props[1].id) {
						/**设置属性一**/
						//移除属性的选项
						$("#prop1Content").html("");
						$("#prop1Name").text(props[0].propertyName + ":");
						var propContent = props[0].propertyCotent.split(/\s+/);
						for(var propc=0; propc<propContent.length; propc++) {
							$("#prop1Content").append($(
									"<button type='button' class='btn btn-default prop' disabled='disabled'>" + propContent[propc] + "</button>"
								));
						}
						/**设置属性二**/
						//移除属性的选项
						$("#prop2Content").html("");
						$("#prop2Name").text(props[1].propertyName + ":");
						var propContent2 = props[1].propertyCotent.split(/\s+/);
						for(var propc=0; propc<propContent2.length; propc++) {
							$("#prop2Content").append($(
									"<button type='button' class='btn btn-default prop' disabled='disabled'>" + propContent2[propc] + "</button>"
								));
						}
					} else {
						/**设置属性一**/
						//移除属性的选项
						$("#prop1Content").html("");
						$("#prop1Name").text(props[1].propertyName + ":");
						var propContent = props[1].propertyCotent.split(/\s+/);
						for(var propc=0; propc<propContent.length; propc++) {
							$("#prop1Content").append($(
									"<button type='button' class='btn btn-default prop' disabled='disabled'>" + propContent[propc] + "</button>"
								));
						}
						/**设置属性二**/
						//移除属性的选项
						$("#prop2Content").html("");
						$("#prop2Name").text(props[0].propertyName + ":");
						var propContent2 = props[0].propertyCotent.split(/\s+/);
						for(var propc=0; propc<propContent2.length; propc++) {
							$("#prop2Content").append($(
									"<button type='button' class='btn btn-default prop' disabled='disabled'>" + propContent2[propc] + "</button>"
								));
						}
					}
				}
				/***设置属性一哪些可点击***/
				//获取实体
				var entities = data.commEntity;
				for(var en=0; en<entities.length; en++) {
					//库存大于0
					if(entities[en].inventory>0) {
						//遍历属性一
						var prop1btn = $("#prop1Content button");
						for(var pb=0; pb<prop1btn.length; pb++) {
							//每个按钮
							var $btn = prop1btn.eq(pb);
							if($btn.text()===entities[en].propty1 || $btn.text()===entities[en].propty2) {
								$btn.removeAttr("disabled");
							}
						}
					}
				}
				/***显示指定的entity***/
				chooesEntity(data, commEntityId);
			} else {
				
			}
		},
		type:"post",
		url:"comm/findCommModelAndEntity.action"
	});
	//点击属性一的按钮
	$("#prop1Content").on("mousedown","button",function(e){
		//alert("ok");
		var $target = $(e.target);
		//如果点击的按钮不可点或已选中样式
		if($target.attr("disabled")==="disabled" || $target.hasClass("active-btn")) {
			return;
		}
		//清除属性二的选中状态
		$("#prop2Content button").removeClass("active-btn");
		//移除其它按钮的选中
		$("#prop1Content button").removeClass("active-btn");
		//console.log("www1");
		$target.addClass("active-btn");
		/***展示可选择的属性二按钮***/
		//获取实体
		var entities = commData.commEntity;
		//获取属性二
		var prop2btn = $("#prop2Content button");
		//每个按钮
		var $btn;
		//先设置属性二全部为不可点
		for(var p2b=0; p2b<prop2btn.length; p2b++) {
			prop2btn.eq(p2b).attr("disabled", "disabled");
		}
		//遍历商品实体
		for(var en=0; en<entities.length; en++) {
			//库存大于0,并且有一个属性为该按钮对应的 属性
			if(entities[en].inventory>0) {
				if(entities[en].propty1===$target.text()) {
					//遍历属性二
					for(var pb=0; pb<prop2btn.length; pb++) {
						//每个按钮
						$btn = prop2btn.eq(pb);
						if($btn.text()===entities[en].propty2) {
							$btn.removeAttr("disabled");
						}
					}
				} else if(entities[en].propty2===$target.text()) {
					//遍历属性二
					for(var pb=0; pb<prop2btn.length; pb++) {
						//每个按钮
						$btn = prop2btn.eq(pb);
						if($btn.text()===entities[en].propty1) {
							$btn.removeAttr("disabled");
						}
					}
				}
			}
		}
	});
	//点击属性二的按钮
	$("#prop2Content").on("mousedown","button",function(e){
		var $target = $(e.target);
		if($target.hasClass("active-btn") || $target.attr("disabled")==="disabled") {
			return;
		}
		//移除其它按钮的选中
		$("#prop2Content button").removeClass("active-btn");
		$target.addClass("active-btn");
		var nowEntityId = findEntityId(commData);
		chooesEntity(commData, nowEntityId);
	});
	//鼠标移入主图片列表则将显示的主图片换为该图片
	$("#main-img-list").on("mouseover","img", function(event){
		$target = $(event.target);
		$("#mainImage").attr("src",$target.attr("src"));
	});
	//点击切换评论和详情等信息
	$("#messageNavigator li").click(function(event){
		var $target = $(event.target);
		//移除选中样式
		$("#messageNavigator li").removeClass("message-navigator-activ");
		$target.addClass("message-navigator-activ");
		switch($target.text().trim()) {
			case "商品详情":
				chooseMessage(1);
				break;
			case "评论信息":
				chooseMessage(2);
				break;
			case "售后服务":
				chooseMessage(3);
				break;
		};
	});
	//鼠标移入检查是否选中商品模板
	$("#amountInput").focus(function(){
		if(findEntityId(commData)<0) {
			//获取属性1名字
			var prop1 = $("#prop1Name").text().trim().replace(":", "");
			//获取属性2名字
			var prop2 = $("#prop2Name").text().trim().replace(":", "");
			disMessage("请先选择 " + prop1 + " " + prop2);
			$("#amountInput").blur();
		}
	});
	//鼠标移除是验证输入数量的合法性
	$("#amountInput").change(function() {
		var value = $("#amountInput").val();
		if(!checkNum(value)) {
			disMessage("请输入正确的数量");
			//$("#amountInput").val(1);
			return;
		}
		var num = parseInt(value);
		if(num>$("#commInventory").text()) {
			disMessage("数量不能大于库存");
			//$("#amountInput").val($("#commInventory").text());
			return;
		}
		if(num>9999) {
			disMessage("数量不能大于 9999");
			//$("#amountInput").val(9999);
			return;
		}
		if(num<1) {
			disMessage("数量不能小于 1");
			//$("#amountInput").val(1);
			return;
		}
	});
	//点击 加 减 按钮改变数量
	$("#amountInputCon span").click(function(event){
		var $target = $(event.target);
		if($target.text().trim() === "+") {
			if(findEntityId(commData)<0) {
				//获取属性1名字
				var prop1 = $("#prop1Name").text().trim().replace(":", "");
				//获取属性2名字
				var prop2 = $("#prop2Name").text().trim().replace(":", "");
				disMessage("请先选择 " + prop1 + " " + prop2);
				return;
			}
			var num = parseInt($("#amountInput").val());
			if(num>$("#commInventory").text()) {
				disMessage("数量不能大于库存");
				return;
			}
			if(num>=9999) {
				disMessage("数量不能大于 9999");
				return;
			}
			$("#amountInput").val(num+1);
		} else if($target.text().trim() === "-") {
			if(findEntityId(commData)<0) {
				//获取属性1名字
				var prop1 = $("#prop1Name").text().trim().replace(":", "");
				//获取属性2名字
				var prop2 = $("#prop2Name").text().trim().replace(":", "");
				disMessage("请先选择 " + prop1 + " " + prop2);
				return;
			}
			var num = parseInt($("#amountInput").val());
			if(num<1) {
				disMessage("数量不能小于 1");
				return;
			}
			$("#amountInput").val(num-1);
		}
		
	});
	//点击加入购物车
	$("#addToShoppingCart").click(function(){
		//选择的实体的id
		var commEntityId = findEntityId(commData);
		//数量
		var amount = $("#amountInput").val();
		if(commEntityId<0) {
			//获取属性1名字
			var prop1 = $("#prop1Name").text().trim().replace(":", "");
			//获取属性2名字
			var prop2 = $("#prop2Name").text().trim().replace(":", "");
			disMessage("请先选择 " + prop1 + " " + prop2);
			return;
		}
		if(!checkNum(amount)) {
			disMessage("请输入正确的数量");
			return;
		}
		if(amount<1) {
			disMessage("数量不能小于 1");
			return;
		}
		if(amount>$("#commInventory").text()) {
			disMessage("数量不能大于库存");
			return;
		}
		if(amount>=9999) {
			disMessage("数量不能大于 9999");
			return;
		}
		var param = window.btoa("commEntityId=" + commEntityId + "&amount=" + amount);
		window.location.href = "login/shopping-cart.action?" + param;
	});
	
	
});