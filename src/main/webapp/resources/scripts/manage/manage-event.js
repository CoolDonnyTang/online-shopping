$(function() {
			//主操作列表点击事件
	$("#managerList").click(function(event) {
		var $target = $(event.target);
		switch($target.text().trim())
		{
		case "分类管理":
			disManageList(1);
		  	break;
		case "添加商品":
			disManageList(2);
			break;
		case "更新今日推荐":
			disManageList(3);
			break;
		case "更新经典品牌":
			disManageList(4);
			break;
		case "更新科成专区":
			disManageList(5);
			break;
		case "更新首页广告区":
			disManageList(6);
			break;
		case "生成搜索关键字":
			disManageList(7);
			break;
		}
	});
	//类操作事件
	$("#kinds_operate").click(function(event){
		var $target = $(event.target);
		if(event.target.nodeName === "A") {
			$("#kinds_operate").children("li").removeClass("active");
			$target.parent().addClass("active");
		}
		switch($target.text().trim())
		{
		case "添加主分类":
		  	operKindsDis(1);
		  	break;
		case "添加子分类":
			//发送请求加载主分类
			$.ajax({
				data:"",
				dataType:"json",
				error:function() {
					alert("连接服务器失败");
						},
						success:function(result) {
							if(result.status === 1) {
								var data = result.data;
console.log(data);
								//清空原来列表
						$("#main_kinds_l1").html("");
						for(var i=0; i<data.length; i++) {
							var $option = $("<option>" +data[i].kindName + "</option>");
							$option.data("id", data[i].id);
							$("#main_kinds_l1").append($option);
							//提交可点
							$("#submitSubKind").removeAttr("disabled");
						}
					} else {
						alert(result.info);
						//提交按钮不可点
						$("#submitSubKind").attr("disabled", "disabled");
					}
				},
				type:"post",
				url:"manage/findMain.action"
			});
		 	operKindsDis(2);
		  	break;
		case "删除主分类":
			//发送请求加载主分类
			$.ajax({
				data:"",
				dataType:"json",
				error:function() {
					alert("连接服务器失败");
				},
				success:function(result) {
					if(result.status === 1) {
						var data = result.data;
						//清空原来列表
						$("#main_kinds_l2").html("");
						for(var i=0; i<data.length; i++) {
							var $option = $("<option>" +data[i].kindName + "</option>");
							$option.data("id", data[i].id);
							$("#main_kinds_l2").append($option);
							//删除可点
							$("#deleteMain").removeAttr("disabled");
						}
					} else {
						alert(result.info);
						//删除按钮不可点
						$("#deleteMain").attr("disabled", "disabled");
					}
				},
				type:"post",
				url:"manage/findMain.action"
			});
		  	operKindsDis(3);
		  	break;
		case "删除子分类":
		  	operKindsDis(4);
		  	break;
		}
	});
	//提交新建主类事件
	$("#sub_main").click(function(){
		var mainText = $("#main-kinds").val().trim();
		var subText = $("#subsidiary_kinds1").val().trim();
		if(""===mainText || ""===subText) {
			alert("主类和子类均不可为空");
			return;
		}
		$.ajax({
			data:{mainName:mainText,subName:subText},
			dataType:"json",
			error:function() {
				alert("连接服务器失败");
			},
			success:function(result) {
				if(result.status === 1) {
					$("#main-kinds").val("");
					$("#subsidiary_kinds1").val("");
					alert("添加成功");
				} else {
					alert(result.info);
				}
			},
			type:"post",
			url:"manage/addMainKind.action"
		});
	});
	//提交辅类提交事件
	$("#submitSubKind").click(function() {
		var subText = $("#subsidiary_kinds2").val().trim();
		var mainId = $("#main_kinds_l1 option:selected").data("id");
		if(subText === "" || undefined=== mainId) {
			alert("输入有误或操作非法");
			return;
		}
		$.ajax({
			data:{mainId:mainId,subName:subText},
			dataType:"json",
			error:function() {
				alert("连接服务器失败");
			},
			success:function(result) {
				if(result.status === 1) {
					$("#subsidiary_kinds2").val("");
					alert("添加成功");
				} else {
					alert(result.info);
				}
			},
			type:"post",
			url:"manage/addSubKind.action"
		});
	});
	//删除主类事件
	$("#deleteMain").click(function() {
		var mainId = $("#main_kinds_l2 option:selected").data("id");
		if(undefined=== mainId) {
		alert("不可执行该操作");
			return;
		}
		$.ajax({
			data:{mainId:mainId},
			dataType:"json",
			error:function() {
				alert("连接服务器失败");
			},
			success:function(result) {
				if(result.status === 1) {
					alert("删除成功");
				} else {
					alert(result.info);
				}
			},
			type:"post",
			url:"manage/deleteMain.action"
		});
	});
	/**
	 * 添加商品相关js
	 **/
	//切换添加商品模板和添加商品实体
	$("#commodityOperateList").click(function(event) {
		var $target = $(event.target);
//alert(event.target.nodeName);
		if(event.target.nodeName === "A") {
			$("#commodityOperateList").children("li").removeClass("active");
			$target.parent().addClass("active");
		}
		switch($target.text().trim())
		{
		case "添加商品模板":
			//发送请求加载分类
			$.ajax({
				data:"",
				dataType:"json",
				error:function() {
					alert("分类加载失败");
				},
				success:function(result) {
					if(result.status === 1) {
						var data = result.data;
						//清空列表
						$("#commodityMainKinds option").remove();
						for(var i=0; i<data.length; i++) {
							var $option;
							if(i===0) {
								$option = $("<option selected='selected' value="+ data[i].id +">"+data[i].kindName+"</option>");
								//清空列表
								$("#commoditySubKinds option").remove();
								for(var j=0; j<data[i].subKinds.length; j++) {
									var $option2 ;
									if(j === 0) {
										$option2 = $("<option selected='selected' value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
									} else {
										$option2 = $("<option value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
									}
									$("#commoditySubKinds").append($option2);
								}
							} else {
								$option = $("<option value="+ data[i].id +">"+data[i].kindName+"</option>");
							}
							$("#commodityMainKinds").data("data", data);
							$("#commodityMainKinds").append($option);
						} 
					}else {
							
					}
				},
				type:"post",
				url:"manage/findMain.action",
			});
			$("#addCommodityModel").show();
			$("#addCommodityEntity").hide();
		  	break;
		case "添加商品实体":
			//发送请求加载分类
			$.ajax({
				data:"",
				dataType:"json",
				error:function() {
					alert("分类加载失败");
				},
				success:function(result) {
					if(result.status === 1) {
						var data = result.data;
						//清空列表
						$("#commEntityMainKinds option").remove();
						for(var i=0; i<data.length; i++) {
							var $option;
							if(i===0) {
								$option = $("<option selected='selected' value="+ data[i].id +">"+data[i].kindName+"</option>");
								//清空列表
								$("#commEntitySubKinds2 option").remove();
								for(var j=0; j<data[i].subKinds.length; j++) {
									var $option2 ;
									if(j === 0) {
										$option2 = $("<option selected='selected' value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
									} else {
										$option2 = $("<option value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
									}
									$("#commEntitySubKinds2").append($option2);
								}
							} else {
								$option = $("<option value="+ data[i].id +">"+data[i].kindName+"</option>");
							}
							$("#commEntityMainKinds").data("data", data);
							$("#commEntityMainKinds").append($option);
						}
						/**
						*	加载模板默认信息
						*/
						//子分类下拉选的值
						var subKindId = $("#commEntitySubKinds2 option:selected").val();
						$.ajax({
							data:{"subKindId":subKindId},
							dataType:"json",
							error:function(){
								alert("连接服务器失败");
							},
							success:function(result){
								if(result.status === 1) {
									var data = result.data;
									if(data.length<=0) {
										alert("当前分类下未建立商品模板");
										return;
									}
									//清空品牌下拉选
									$("#commEntityBrand option").remove();
									for(var i=0; i<data.length; i++) {
										var $option ;
										if(i === 0) {
											$option = $("<option selected='selected'>"+ data[i].brandName +"</option>");
										} else {
											$option = $("<option>"+ data[i].brandName +"</option>");
										}
										$("#commEntityBrand").append($option);
										$("#commEntityBrand").data("data", data);
									}
									//清空商品标题下拉选
									$("#commEntityTitle option").remove();
									for(var j=0; j<data[0].commTitles.length; j++) {
										var $titleOption ;
										if(j === 0) {
											$titleOption = $("<option selected='selected' value="+ data[0].commTitles[j].commId +">" + data[0].commTitles[j].commTitle +"</option>");
										} else {
											$titleOption = $("<option value="+ data[0].commTitles[j].commId +">" + data[0].commTitles[j].commTitle +"</option>");
										}
										$("#commEntityTitle").append($titleOption);
									}
									/**
									*	初始化属性下拉选
									*/
									$("#popLable1").text(data[0].commTitles[0].prop1.propName);
									//清空属性一下拉选
									$("#commEntityProp1 option").remove();
									for(var p1=0; p1<data[0].commTitles[0].prop1.props.length; p1++) {
										var $prop1Option ;
										if(p1 === 0) {
											$prop1Option = $("<option selected='selected'>" + data[0].commTitles[0].prop1.props[p1] +"</option>");
										} else {
											$prop1Option = $("<option>" + data[0].commTitles[0].prop1.props[p1] +"</option>");
										}
										$("#commEntityProp1").append($prop1Option);
									}
									$("#popLable2").text(data[0].commTitles[0].prop2.propName);
									//清空属性一下拉选
									$("#commEntityProp2 option").remove();
									for(var p2=0; p2<data[0].commTitles[0].prop2.props.length; p2++) {
										var $prop2Option ;
										if(p2 === 0) {
											$prop2Option = $("<option selected='selected'>" + data[0].commTitles[0].prop2.props[p2] +"</option>");
										} else {
											$prop2Option = $("<option>" + data[0].commTitles[0].prop2.props[p2] +"</option>");
										}
										$("#commEntityProp2").append($prop2Option);
									}
								} else {
									
								}
							},
							type:"post",
							url:"manage/selectMessage4addCommEntity.action"
						}); 
					}else {
							
					}
				},
				type:"post",
				url:"manage/findMain.action",
			});
			$("#addCommodityModel").hide();
			$("#addCommodityEntity").show();
			break;
		}
	});
	//添加商品模板选择类别联动菜单
	$("#commodityMainKinds").change(function() {
		var id = $("#commodityMainKinds option:selected").val();
		var data = $("#commodityMainKinds").data("data");
console.log(id);
				for(var i=0; i<data.length; i++) {
					if(data[i].id == id) {
						//清空子列表
				$("#commoditySubKinds option").remove();
				for(var j=0; j<data[i].subKinds.length; j++) {
//console.log(data[i].subKinds[j].kindName);
					var $option2 ;
					if(j === 0) {
						$option2 = $("<option selected='selected' value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
					} else {
						$option2 = $("<option value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
					}
					$("#commoditySubKinds").append($option2);
				}
			}
		}
	});
	
	//提交模板信息
	$("#submitCommodityMessage").click(function() {
		//标题
		var title = $("#commodityTitle").val().trim();
		//所属类别
		var subKind = $("#commoditySubKinds option:selected").val().trim();
		//属性1
		var propertyName1 = $("#property1").val().trim();
		var propertyContent1 = $("#property1Detail").val().trim();
		//属性2
		var propertyName2 = $("#property2").val().trim();
		var propertyContent2 = $("#property2Detail").val().trim();
		//品牌
		var brand = $("#brand").val().trim();
		//详情图片组
		var detailImage = $("#detailImageList input[class='detailImage']");
		if("" === title) {
			alert("标题不可为空");
			return;
		}
		if(isNaN(subKind) || subKind<0) {
			alert("出现一个异常，请刷新页面重试");
			return;
		}
		var counter1 = 0;
		var imageReg = /^.+\.(png)|(jpg)|(jpeg)|(gif)$/i;
		var imageName = "";
		if(propertyName1 === "" && propertyName2 === "") {
			alert("请至少填写一个属性项");
			return;
		}
		if(!(propertyName1 === "") && "" === propertyContent1) {
			alert("请填写属性一详情");
			return;
		}
		if(!(propertyName2 === "") && "" === propertyContent2) {
			alert("请填写属性二详情");
			return;
		}
		if(brand === "") {
			alert("请填写所属品牌");
			return;
		}
		counter1 = 0;
		imageName = "";
		for(var i=0; i<detailImage.length; i++) {
			imageName = detailImage.eq(i).val().trim();
			if(!(imageName === "")) {
				if(!(imageReg.test(imageName))) {
					alert("商品主图片出现非图片格式文件");
					return;
				} else {
					counter1 = counter1 + 1;
				}
			}
		}
		if(counter1 <= 0) {
			alert("商品详情图片至少选择一张");
			return;
		}
		//所有验证通过则提交表单
		//$("#commodityMessage").submit();
		 var data =  new FormData($("#commodityMessage").get(0));
		 $.ajax({
            url: "manage/addCommodity.action",
            type: "post",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            error: function() {
            	alert("连接服务器失败");
            },
            success:function(result){
                if(result.status === 1) {
                	$("#commodityMessage input").val("");
                	$("#commodityMessage textarea").val("");
                }
                alert(result.info);
            }
        }); 
	});
	//添加商品实体选择类别联动菜单
	$("#commEntityMainKinds").change(function() {
		var id = $("#commEntityMainKinds option:selected").val();
		var data = $("#commEntityMainKinds").data("data");
console.log(id);
				for(var i=0; i<data.length; i++) {
					if(data[i].id == id) {
						//清空子列表
				$("#commEntitySubKinds2 option").remove();
				for(var j=0; j<data[i].subKinds.length; j++) {
//console.log(data[i].subKinds[j].kindName);
					var $option2 ;
					if(j === 0) {
						$option2 = $("<option selected='selected' value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
					} else {
						$option2 = $("<option value="+ data[i].subKinds[j].id +">"+data[i].subKinds[j].kindName+"</option>");
					}
					$("#commEntitySubKinds2").append($option2);
					
				}
			}
		}
	});
	//子分类列表改变重新加载品牌和商品标题以及属性
	$("#commEntitySubKinds2").change(function(){
		//子分类下拉选的值
		var subKindId = $("#commEntitySubKinds2 option:selected").val();
		$.ajax({
			data:{"subKindId":subKindId},
			dataType:"json",
			error:function(){
				alert("连接服务器失败");
			},
			success:function(result){
				if(result.status === 1) {
					var data = result.data;
					if(data.length <= 0) {
						alert("当前分类下未建立商品模板");
						//清空品牌下拉选
						$("#commEntityBrand option").remove();
						//清空商品标题下拉选
						$("#commEntityTitle option").remove();
						$("#popLable1").text("属性一");
						//清空属性一下拉选
						$("#commEntityProp1 option").remove();
						$("#popLable2").text("属性二");
						//清空属性一下拉选
						$("#commEntityProp2 option").remove();
						return;
					}
//console.log("data::" + data.length);
					//清空品牌下拉选
					$("#commEntityBrand option").remove();
					for(var i=0; i<data.length; i++) {
						var $option ;
						if(i === 0) {
							$option = $("<option selected='selected'>"+ data[i].brandName +"</option>");
						} else {
							$option = $("<option>"+ data[i].brandName +"</option>");
						}
						$("#commEntityBrand").append($option);
						$("#commEntityBrand").data("data", data);
					}
					//清空商品标题下拉选
					$("#commEntityTitle option").remove();
					for(var j=0; j<data[0].commTitles.length; j++) {
						var $titleOption ;
						if(j === 0) {
							$titleOption = $("<option value="+ data[0].commTitles[j].commId +" selected='selected'>" + data[0].commTitles[j].commTitle +"</option>");
						} else {
							$titleOption = $("<option value="+ data[0].commTitles[j].commId +">" + data[0].commTitles[j].commTitle +"</option>");
						}
						$("#commEntityTitle").append($titleOption);
					}
					/**
					*	初始化属性下拉选
					*/
					$("#popLable1").text(data[0].commTitles[0].prop1.propName);
					//清空属性一下拉选
					$("#commEntityProp1 option").remove();
					for(var p1=0; p1<data[0].commTitles[0].prop1.props.length; p1++) {
						var $prop1Option ;
						if(p1 === 0) {
							$prop1Option = $("<option selected='selected'>" + data[0].commTitles[0].prop1.props[p1] +"</option>");
						} else {
							$prop1Option = $("<option>" + data[0].commTitles[0].prop1.props[p1] +"</option>");
						}
						$("#commEntityProp1").append($prop1Option);
					}
					$("#popLable2").text(data[0].commTitles[0].prop2.propName);
					//清空属性二下拉选
					$("#commEntityProp2 option").remove();
					for(var p2=0; p2<data[0].commTitles[0].prop2.props.length; p2++) {
						var $prop2Option ;
						if(p2 === 0) {
							$prop2Option = $("<option selected='selected'>" + data[0].commTitles[0].prop2.props[p2] +"</option>");
						} else {
							$prop2Option = $("<option>" + data[0].commTitles[0].prop2.props[p2] +"</option>");
						}
						$("#commEntityProp2").append($prop2Option);
					}
				} else {
					
				}
			},
			type:"post",
			url:"manage/selectMessage4addCommEntity.action"
		});
	});
	/**
	*	添加商品实体的品牌、标题、属性三级联动下拉选
	*/
	//三级联动下拉选之品牌改变
	$("#commEntityBrand").change(function(){
		var data = $("#commEntityBrand").data("data");
		var brandName = $("#commEntityBrand option:selected").text().trim();
		for(var i=0; i<data.length; i++) {
			if(data[i].brandName === brandName) {
				//清空商品标题下拉选
				$("#commEntityTitle option").remove();
				for(var j=0; j<data[i].commTitles.length; j++) {
					var $titleOption ;
					if(j === 0) {
						$titleOption = $("<option value="+ data[i].commTitles[j].commId +" selected='selected'>" + data[i].commTitles[j].commTitle +"</option>");
					} else {
						$titleOption = $("<option value="+ data[i].commTitles[j].commId +">" + data[i].commTitles[j].commTitle +"</option>");
					}
					$("#commEntityTitle").append($titleOption);
				}
				/**
				*	初始化属性下拉选
				*/
				$("#popLable1").text(data[i].commTitles[0].prop1.propName);
				//清空属性一下拉选
				$("#commEntityProp1 option").remove();
				for(var p1=0; p1<data[i].commTitles[0].prop1.props.length; p1++) {
					var $prop1Option ;
					if(p1 === 0) {
						$prop1Option = $("<option selected='selected'>" + data[i].commTitles[0].prop1.props[p1] +"</option>");
					} else {
						$prop1Option = $("<option>" + data[i].commTitles[0].prop1.props[p1] +"</option>");
					}
					$("#commEntityProp1").append($prop1Option);
				}
				$("#popLable2").text(data[i].commTitles[0].prop2.propName);
				//清空属性一下拉选
				$("#commEntityProp2 option").remove();
				for(var p2=0; p2<data[i].commTitles[0].prop2.props.length; p2++) {
					var $prop2Option ;
					if(p2 === 0) {
						$prop2Option = $("<option selected='selected'>" + data[i].commTitles[0].prop2.props[p2] +"</option>");
					} else {
						$prop2Option = $("<option>" + data[i].commTitles[0].prop2.props[p2] +"</option>");
					}
					$("#commEntityProp2").append($prop2Option);
				}
			}
		}
	});
	//三级联动下拉选之商品标题改变
	$("#commEntityTitle").change(function(){
		var data = $("#commEntityBrand").data("data");
		var brandName = $("#commEntityBrand option:selected").text().trim();
		var titleName = $("#commEntityTitle option:selected").text().trim();
		for(var i=0; i<data.length; i++) {
			if(data[i].brandName === brandName) {
				for(var j=0; j<data[i].commTitles.length; j++) {
					if(data[i].commTitles[j].commTitle === titleName) {
						/**
						*	初始化属性下拉选
						*/
						$("#popLable1").text(data[i].commTitles[j].prop1.propName);
						//清空属性一下拉选
						$("#commEntityProp1 option").remove();
						for(var p1=0; p1<data[i].commTitles[j].prop1.props.length; p1++) {
							var $prop1Option ;
							if(p1 === 0) {
								$prop1Option = $("<option selected='selected'>" + data[i].commTitles[j].prop1.props[p1] +"</option>");
							} else {
								$prop1Option = $("<option>" + data[i].commTitles[j].prop1.props[p1] +"</option>");
							}
							$("#commEntityProp1").append($prop1Option);
						}
						$("#popLable2").text(data[i].commTitles[j].prop2.propName);
						//清空属性一下拉选
						$("#commEntityProp2 option").remove();
						for(var p2=0; p2<data[i].commTitles[0].prop2.props.length; p2++) {
							var $prop2Option ;
							if(p2 === 0) {
								$prop2Option = $("<option selected='selected'>" + data[i].commTitles[j].prop2.props[p2] +"</option>");
							} else {
								$prop2Option = $("<option>" + data[i].commTitles[j].prop2.props[p2] +"</option>");
							}
							$("#commEntityProp2").append($prop2Option);
						}
					}
				}
				
			}
		}
	});
	//提交实体信息
	$("#submitCommEntityMessage").click(function(){
		//商品id
		var commId = $("#commEntityTitle option:selected").val();
		//商品属性
		var prop1 = $("#commEntityProp1 option:selected").text();
		var prop2 = $("#commEntityProp2 option:selected").text();
		//主图片组
		var mainImages = $("#mainImageList input[class='mainImage']");
		//库存
		var inventory = $("#commEntityInventory").val().trim();
		//售价
		var price = $("#myPrice").val().trim();
		//市场价
		var marketPrice = $("#marketPrice").val().trim();
		//参数
		var paramDetail = $("#paramDetail").val().trim();
		/**
		*	数据校验
		*/
		//主图片校验
		var counter1 = 0;
		var imageReg = /^.+\.(png)|(jpg)|(jpeg)|(gif)$/i;
		var imageName = "";
		var mainImageNames = new Array(mainImages.length);
		for(var i=0; i<mainImages.length; i++) {
			imageName = mainImages.eq(i).val().trim();
			if(!(imageName === "")) {
				if(!(imageReg.test(imageName))) {
					alert("商品主图片出现非图片格式文件");
					return;
				} else {
					counter1 = counter1 + 1;
					mainImageNames[i] = imageName;
				}
			}
		}
		if(!checkInteger(inventory)) {
			alert("库存有效值为 1-999999");
			return;
		}
		if(!checkBigDicemal(price)) {
			alert("售价有效值为 0.00-999999999.00");
			return;
		}
		if(counter1 <= 0) {
			alert("商品主图片至少选择一张");
			return;
		}
		if(paramDetail === "") {
			alert("请填写参数详情");
			return;
		}
		var data =  new FormData($("#commEntityForm").get(0));
		data.append("propty1",prop1);
		data.append("propty2",prop2);
		$.ajax({
			url: "manage/addCommEntity.action",
            type: "post",
            data: data,
            cache: false,
            contentType: false,    //不可缺,必须false才会自动加上正确的Content-Type
            processData: false,    //不可缺,必须false才会避开jQuery对 formdata 的默认处理, XMLHttpRequest会对 formdata 进行正确的处理
            error: function() {
            	alert("连接服务器失败");
            },
            success:function(result){
                if(result.status === 1) {
                	//成功则清除键入的信息
                	$("#mainImageList input[class='mainImage']").val("");
                	$("#commEntityInventory").val("");
                	$("#myPrice").val("");
                	$("#marketPrice").val("");
					$("#paramDetail").val("");
                }
                alert(result.info);
            }
		});
	});
	
	//提交今日推荐商品的id
	$("#submitRecommendCommEntityId").click(function() {
		var data = [];
		var $input = $("#recommandList input");
		var valueId;
		for(var i=0; i<$input.length; i++) {
			valueId = $input.eq(i).val().trim();
			if(!checkNum(valueId)){
				alert("存在非法的输入id");
				return;
			}
			data.push(valueId);
		}
		console.log(data);
		$.ajax({
			url: "manage/addRcommendCommEntity.action",
            type: "post",
            data: {ids:data, recommendType:1},
            error: function() {
            	alert("连接服务器失败11");
            },
            success:function(result){
                if(result.status === 1) {
                	alert("添加成功");
                }
            }
		});
	});
	
	/**
	* 更新品牌推荐区
	*/
	//发送请求加载商品品牌，生成商品品牌下拉选
	$.ajax({
		url: "manage/queryBrand.action",
        type: "post",
        data: "",
        error: function() {
        	alert("连接服务器失败11");
        },
        success:function(result){
            if(result.status === 1) {
            	var data = result.data;
            	var $option;
            	//清空下拉列表
            	$("#randName").html("");
            	for(var i=0; i<data.length; i++) {
            		if(i===0) {
            			$option = $("<option selected='selected' value='" + data[i] + "'>"+ data[i] +"</option>");
            		} else {
            			$option = $("<option value='" + data[i] + "'>"+ data[i] +"</option>");
            		}
            		$("#randName").append($option);
            	}
            }
        }
	});
	//提交品牌推荐数据
	$("#submitRecommendBrand").click(function(){
		var data =  new FormData($("#recommandBrand").get(0));
		$.ajax({
			url: "manage/addRecommendBrand.action",
            type: "post",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            error: function() {
            	alert("连接服务器失败");
            },
            success:function(result){
                if(result.status === 1) {
                	//成功则清除键入的信息
                	alert("添加成功");
                }
            }
		});
	});
	
	//提交爱购吃货商品的id
	$("#submitRecommendFoodCommEntityId").click(function() {
		var data = [];
		var $input = $("#recommandFoodList input");
		var valueId;
		for(var i=0; i<$input.length; i++) {
			valueId = $input.eq(i).val().trim();
			if(!checkNum(valueId)){
				alert("存在非法的输入id");
				return;
			}
			data.push(valueId);
		}
		console.log(data);
		$.ajax({
			url: "manage/addRcommendCommEntity.action",
            type: "post",
            data: {ids:data, recommendType:2},
            error: function() {
            	alert("连接服务器失败11");
            },
            success:function(result){
                if(result.status === 1) {
                	alert("添加成功");
                }
            }
		});
	});
	
	//提交首页广告图片
	$("#submitAdImage").click(function(){
		var flag = false;
		var $input = $("#adImageList input");
		for(var i=0; i<$input.length; i++) {
			if($input.eq(i).val().trim() !== "") {
				flag = true;
			}
		}
		if(!flag) {
			alert("请至少选择一张图片");
			return;
		}
		var data = new FormData($("#adImageForm").get(0));
		$.ajax({
			url: "manage/addAdImages.action",
            type: "post",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            error: function() {
            	alert("连接服务器失败");
            },
            success:function(result){
                if(result.status === 1) {
                	//成功则清除键入的信息
                	alert("添加成功");
                }
            }
		});
	});
	
	//生成搜索关键字的按钮点击事件
	$(".searchKey").click(function(event){
		var $target = $(event.target);
		console.log($target.val());
		$.ajax({
			url: "manage/generateSearchKey.action",
            type: "post",
            data: {type:$target.val()},
            error: function() {
            	alert("连接服务器失败");
            },
            success:function(result){
                if(result.status === 1) {
                	//成功则清除键入的信息
                	alert("添加成功");
                } else {
                	alert(result.info);
                }
            }
		});
	});
	
	
	
});