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