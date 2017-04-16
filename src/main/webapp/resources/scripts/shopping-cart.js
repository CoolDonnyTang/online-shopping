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
	var $allTr = $tbodyNode.find("tr");
	var totalPrice = 0;
	var trPrice;
	for(var i=0; i<$allTr.length; i++) {
		trPrice = $allTr.eq(i).find(".entityTotalPrice").text().replace("￥", "").trim();
		totalPrice = trPrice * 1 + totalPrice;
	}
	console.log("++++++" + totalPrice);
	$("#totalPrice").text("￥"+totalPrice);
}













