function operKindsDis(num) {
	if(1 === num) {
		$("#add_main").show();
		$("#add_subsidiary").hide();
		$("#delete_main").hide();
		$("#delete_subsidiary").hide();
	} else if(2 === num) {
		$("#add_main").hide();
		$("#add_subsidiary").show();
		$("#delete_main").hide();
		$("#delete_subsidiary").hide();
		$.ajax({
			data:"",
			dataType:"jason",
			error:function() {
				
			},
			success:function(result) {
				if(result.status === 1) {
					
				}
			},
			type:"post",
			url:""
		});
	} else if(3 === num) {
		$("#add_main").hide();
		$("#add_subsidiary").hide();
		$("#delete_main").show();
		$("#delete_subsidiary").hide();
	} else if(4 === num) {
		$("#add_main").hide();
		$("#add_subsidiary").hide();
		$("#delete_main").hide();
		$("#delete_subsidiary").show();
	}
}

function disManageList(num) {
	if(num === 1) {
		$("#kinds_content").show();
		$("#commodityContent").hide();
		$("#recommendContent").hide();
		$("#recommendBrandContent").hide();
		$("#recommendFoodContent").hide();
		$("#adContent").hide();
	} else if(num === 2) {
		$("#kinds_content").hide();
		$("#commodityContent").show();
		$("#recommendContent").hide();
		$("#recommendBrandContent").hide();
		$("#recommendFoodContent").hide();
		$("#adContent").hide();
	} else if(num === 3) {
		$("#kinds_content").hide();
		$("#commodityContent").hide();
		$("#recommendContent").show();
		$("#recommendBrandContent").hide();
		$("#recommendFoodContent").hide();
		$("#adContent").hide();
	} else if(num === 4) {
		$("#kinds_content").hide();
		$("#commodityContent").hide();
		$("#recommendContent").hide();
		$("#recommendBrandContent").show();
		$("#recommendFoodContent").hide();
		$("#adContent").hide();
	} else if(num === 5) {
		$("#kinds_content").hide();
		$("#commodityContent").hide();
		$("#recommendContent").hide();
		$("#recommendBrandContent").hide();
		$("#recommendFoodContent").show();
		$("#adContent").hide();
	} else if(num === 6) {
		$("#kinds_content").hide();
		$("#commodityContent").hide();
		$("#recommendContent").hide();
		$("#recommendBrandContent").hide();
		$("#recommendFoodContent").hide();
		$("#adContent").show();
	}
}
