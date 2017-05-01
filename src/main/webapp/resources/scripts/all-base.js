function disUserName() {
	var user = $.cookie('userName');
	if(undefined != user) {
		$("#userAndLogExit").show();
		$("#userName").text(user);
		$("#loginAndRegister").hide();
	}
}
disUserName();

