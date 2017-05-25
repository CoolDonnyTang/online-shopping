$(function() {
	var data;
	//加载分类和分类的单击事件
	kindLoadAndOperation();
	//加载今日推荐
	loadTodayRecommend();
	//加载推荐品牌
	loadRecommendBrand();
	//加载"爱购吃货"
	loadRecommendFood()
	//加载销量排行
	loadSalesTop2();
	//加载广告图片
	loadIndexAdImage();
	//点击商品进入商品详情的点击事件
	$("#recommendList, #recommendFood, #weeklySales").on("click",".thumbnail",function(event){
		var $target = $(event.target);
		var $container = $target.parents(".commodities-list");
		window.location.href="detail.html?" 
			+ window.btoa("commId=" + $container.data("commId") + "&commEntityId=" +$container.data("commEntityId"));
	});
});