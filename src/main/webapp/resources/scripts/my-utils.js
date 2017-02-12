function checkEmail(mail) {
	if(mail===null || mail===undefined) {
		return false
	}
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(mail);
}