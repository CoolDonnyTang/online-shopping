$("#basic-addon2").click(function(){
	var key = $("#basic-addon2").prev().val();
	window.location.href="commodity-list.html?" + window.btoa(window.encodeURIComponent("nameKey=" + key));
});