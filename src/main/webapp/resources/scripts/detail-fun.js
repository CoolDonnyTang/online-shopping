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
							"<div class='thumbnail col-sm-3 col-md-3 main-img-lis'>" +
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
								"<li role='presentation' class='text-center col-md-3'>" + param[k].paramContent + "</li>"
							));
				}
			}
		}
	}
	//设置主图片宽等于高
//	var $mainImages = $("#main-img-dis img");
//	var width;
//	for(var n=0; n<$mainImages.length; n++) {
//		width = $mainImages.eq(n).width();
//		console.log("w"　+　$mainImages.eq(n).width());
//		$mainImages.eq(n).height(width);
//	}
}