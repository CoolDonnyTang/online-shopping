/**
 * 发送请求改变购物车商品数量
 * tangyanrentyr
 * 2017年4月16日
 * @param amount
 * @param entityId
 * @param inputNode
 */
function changeAmount(amount, entityId, inputNode) {
	var oldNum = inputNode.data("oldNum");
	if(amount == oldNum) {
		return;
	}
	console.log(amount + "," + entityId);
	$.ajax({
		data:{amount:amount, entityId:entityId},
		dataType:"json",
		error:function(){
			
		},
		success:function(result){
			if(result.status === 1) {
				inputNode.val(amount);calculateEntityPrice(inputNode.parents("tr"));
			} else {
				inputNode.val(oldNum);
			}
		},
		type:"post",
		url:"../checkLogin/updateShoppingCart.action"
	});
}

function calculateEntityPrice($trNode) {
	var amount = $trNode.find(".amount-input").val().trim();
	var price = $trNode.find(".unitPrice").text().replace("￥", "").trim();
console.log(amount + "<>" + price);
	if(amount!=="" && price!=="" && price!==undefined && price!==null) {
		var totalPrice = amount * price;
		$trNode.find(".entityTotalPrice").text("￥"+totalPrice);
	}
	calculateTotalPrice($trNode.parents("tbody"));
}

function calculateTotalPrice($tbodyNode) {
	var $allTr = $tbodyNode.find("input:checked[name='checkProject']");
	var totalPrice = 0;
	var trPrice;
	for(var i=0; i<$allTr.length; i++) {
		trPrice = $allTr.eq(i).parents("tr").find(".entityTotalPrice").text().replace("￥", "").trim();
		totalPrice = trPrice * 1 + totalPrice;
	}
	$("#totalPrice").text("￥"+totalPrice);
}

function addToCollection(commEntityId, pageUrl) {
	//发送请求将商品加入收藏
	$.ajax({
		data:{commEntityId:commEntityId, pageUrl:pageUrl, nowStatus:false},
		dataType:"json",
		error:function(result){
			console.log(result);
		},
		success:function(result){
			console.log(result);
			if(result.status === 1) {
				
			}
		},
		type:"post",
		url:"../checkLogin/collectionOperate.action"
	});
}

function addMoreToCollection(commEntitiesId, pageUrl) {
	console.log(commEntitiesId);
	//发送请求将商品加入收藏
	$.ajax({
		data:{commEntitiesId:commEntitiesId, pageUrl:pageUrl},
		dataType:"json",
		error:function(result){
			console.log(result);
		},
		success:function(result){
			console.log(result);
			if(result.status === 1) {
				
			}
		},
		type:"post",
		url:"../checkLogin/addMoreToCollection.action"
	});
}










