$(function(){
	//使全选按钮不选中
	$("#cartContainer input[name='checkAll']").prop("checked",false);
	
	//发送请求加载购物车数据
	$.ajax({
		data:"",
		dataType:"JSON",
		error:function() {
		
		},
		success:function(result){
			if(result.status === 1) {
				//清空列表
				$("#cartList").html("");
				var data = result.data;
				if(data===undefined || data===null || data.length<1) {
					$("#cartContainer").html("<div><img src='../resources/images/empty-shopping-cart.png' style='width:20%'></div><div><h4>购物车为空</h4></div>");
				} else {
					//清空列表
					$("#cartList").html("");
					for(var i=0; i<data.length; i++) {
						var $node = $("<tr>"+
						   				"<td><input type='checkbox'/ name='checkProject'></td>"+
						   				"<td>"+
						   					"<div class='media cart-commodity'>"+
											  "<div class='media-left media-middle'>"+
											     "<img class='media-object cart-image' src="+ data[i].mainUrl +">"+
											  "</div>"+
											  "<div class='media-body comment'>"+
											    "<p><b>"+ data[i].commTitle +"</b></p>"+
											    "<div class='attribute_'>"+
											    	"<span>"+ data[i].commBrand +"&nbsp;: </span>"+
											    	"<span>"+ data[i].prop1 +"&nbsp;</span>"+
											    	"<span>"+ data[i].prop2 +"&nbsp;</span>"+
											    "</div>"+
											  "</div>"+
											"</div>"+
						   				"</td>"+
						   				"<td class='unitPrice'>￥"+ data[i].price +"</td>"+
						   				"<td>"+
											"<div>"+
												"<button type='button' class='btn btn-default btn-xs amount-sub'>-</button>"+
												"<input type='text' class='form-control cart-input amount-input' value='"+ data[i].amount +"'/>"+
												"<button type='button' class='btn btn-default btn-xs amount-add'>+</button>"+
											"</div>"+
						   				"</td>"+
						   				"<td><b class='entityTotalPrice'>￥zx</b></td>"+
						   				"<td>"+ data[i].inventory +"件</td>"+
						   				"<td>"+
						   					"<button type='button' class='btn btn-default btn-xs my-btn-1 entity-delete'>删除</button>"+
						   					"<button type='button' class='btn btn-default btn-xs my-btn-1 entity-collection'>移入关注</button>"+
						   				"</td>"+
						   			"</tr>");
						$node.data("entityId",data[i].entityId);
						$node.data("commentityId",data[i].commentityId);
						console.log(data[i].entityId);
						$("#cartList").append($node);
						calculateEntityPrice($node);
					}
				}
			} else {
				$("#cartContainer").html("</div><div><h4>"+ result.info +"</h4></div>");
			}
		},
		type:"post",
		url:"../checkLogin/get-shopping-cart-entity.action"
	});
	
	//选择框单击事件
	$("#cartContainer").on("click","input[name='checkProject'],input[name='checkAll']",function(event){
		//事件源
		var $node = $(event.target);
		//所有项目的选择框
		var $checkProject = $("input[name='checkProject']");
		//全选选择框
		var $checkAll = $("input[name='checkAll']");
		if($node.attr("name") == "checkAll") {
			if($node.is(':checked')) {
				$checkProject.prop("checked",true);
			} else {
				$checkProject.prop("checked",false);
			}
		}
		if($node.attr("name") == "checkProject") {
			for(var i=0; i<$checkProject.length; i++) {
				if(!($checkProject.eq(i).is(':checked'))) {
					$checkAll.prop("checked", false);
					//重新计算总价
					calculateTotalPrice($("#cartList"));
					return;
				}
			}
			$checkAll.prop("checked", true);
		}
		//重新计算总价
		calculateTotalPrice($("#cartList"));
	});
	
	//数量输入框鼠标切入事件记录旧值
	$("#cartContainer").on("focus",".amount-input",function(event) {
		var $target = $(event.target);
		$target.data("oldNum",$target.val());
	});
	
	//数量按钮"- +"单击事件
	$("#cartContainer").on("click",".amount-sub,.amount-add",function(event){
		var $target = $(event.target);
		var entityId = $target.parents("tr").data("entityId");
		var $input; 
		if($target.text().trim() === "+") {
			$input = $target.prev();
			if($input.val().trim() === "") {
				return;
			}
			var num = parseInt($input.val());
			if(num>=9999) {
				$input.data("oldNum",num);
				return;
			}
			$input.data("oldNum",num);
			num = num + 1;
			$input.val("");
			changeAmount(num, entityId, $input);
		} else if($target.text().trim() === "-") {
			$input = $target.next();
			if($input.val().trim() === "") {
				return;
			}
			var num = parseInt($input.val());
			if(num<=1) {
				$input.data("oldNum",num);
				return;
			}
			$input.data("oldNum",num);
			num = num - 1;
			$input.val("");
			changeAmount(num, entityId, $input);
		}
	});
	
	//数量输入框值改变事件
	$("#cartContainer").on("change",".amount-input",function(event) {
		var $target = $(event.target);
		var entityId = $target.parents("tr").data("entityId");
		var value = $target.val().trim();
		if(!checkNum4(value)) {
			$target.val($target.data("oldNum"));
			return;
		}
		changeAmount(value, entityId, $target);
		console.log("ok");
	});
	
	//单击删除按钮删除单个商品
	$("#cartContainer").on("click",".entity-delete",function(event){
		var $tr = $(event.target).parents("tr");
		var entityId = $tr.data("entityId");
		if(entityId !== undefined) {
			$.ajax({
				data:{entityId:entityId},
				dataType:"json",
				error:function(){
					
				},
				success:function(result){
					if(result.status === 1) {
						$tr.remove();
						//重新计算总价
						calculateTotalPrice($("#cartList"));
					}
				},
				type:"post",
				url:"../checkLogin/deleteShoppingCart.action"
			});
		}
	});
	
	//单击移入收藏按钮
	$("#cartContainer").on("click",".entity-collection",function(event){
		alert("ok");
		var $tr = $(event.target).parents("tr");
		var entityId = $tr.data("entityId");
		//调用函数将商品加入收藏
		addToCollection(entityId, window.location.href);
		//触发该商品对应的删除按钮的点击事件
		$tr.find(".entity-delete").click();
	});
	
	//删除选中商品事件
	$("#cartContainer").on("click","#deleteMore",function(){
		var $allChecked = $("#cartList input:checked[name='checkProject']");
		//console.log($allChecked);
		if($allChecked===undefined || $allChecked===null || $allChecked.length<1) {
			return;
		}
		var array = new Array($allChecked.length);
		for(var i=0; i<$allChecked.length; i++) {
			array[i] = $allChecked.eq(i).parents("tr").data("entityId");
		}
		//console.log(array);
		$.ajax({
			data:{entitiesId:array},
			dataType:"json",
			error:function(result){
				//console.log(result);
			},
			success:function(result){
				console.log(result);
				if(result.status === 1) {
					for(var i=0; i<$allChecked.length; i++) {
						$allChecked.eq(i).parents("tr").remove();
						//重新计算总价
						calculateTotalPrice($("#cartList"));
					}
				}
			},
			type:"post",
			url:"../checkLogin/deleteShoppingCarts.action"
		});
	});
	
	//将选中商品加入收藏事件
	$("#cartContainer").on("click","#collecteMore",function(){
		var $allChecked = $("#cartList input:checked[name='checkProject']");
		//console.log($allChecked);
		if($allChecked===undefined || $allChecked===null || $allChecked.length<1) {
			return;
		}
		var array = new Array($allChecked.length);
		for(var i=0; i<$allChecked.length; i++) {
			array[i] = $allChecked.eq(i).parents("tr").data("entityId");
		}
		//将商品加入收藏夹
		addMoreToCollection(array, window.location.href);
		//触发删除选中的购物车商品的按键的单击事件
		$("#deleteMore").click();
	});
	
	//点击结算
	$("#checkout").click(function(){
		var $allChecked = $("#cartList input:checked[name='checkProject']");
		//console.log($allChecked);
		if($allChecked===undefined || $allChecked===null || $allChecked.length<1) {
			return;
		}
		var array = new Array($allChecked.length);
		for(var i=0; i<$allChecked.length; i++) {
			array[i] = $allChecked.eq(i).parents("tr").data("commentityId") + "|" + $allChecked.eq(i).parents("tr").find(".amount-input").val().trim();
		}
		var param = window.btoa(array.join("&"));
		//var nowUrlPath = window.location.pathname;
		//应用路径
		//var appPath = nowUrlPath.substring(0, nowUrlPath.indexOf("/", 1));
		window.location.href = "order-commit.action?" + param;
	});
	
});