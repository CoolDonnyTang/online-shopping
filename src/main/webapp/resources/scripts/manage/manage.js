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
	} else if(num === 2) {
		$("#kinds_content").hide();
		$("#commodityContent").show();
	}
}
