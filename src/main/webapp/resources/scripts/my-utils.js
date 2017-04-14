function checkEmail(mail) {
	if(mail===null || mail===undefined) {
		return false
	}
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(mail);
}
function checkNickname(name) {
	if(name===null || name===undefined) {
		return false
	}
	var reg = /^[\u4E00-\u9FA5A-Za-z0-9_]{4,10}$/;
	return reg.test(name);
}
function checkIdNumber(number) {
	if(number===null || number===undefined) {
		return false
	}
	var reg = /^[1-9]\d{5}[1-2]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|((\d{3}X)|(\d{3}x)))$/;
	return reg.test(number);
}
function checkPassword(pwd) {
	if(pwd===null || pwd===undefined) {
		return false
	}
	var reg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/;
	return reg.test(pwd);
}
function checkCheckCode(checkCode) {
	if(checkCode===null || checkCode===undefined) {
		return false
	}
	var reg = /^[a-zA-Z0-9]{5}$/;
	return reg.test(checkCode);
}
function checkInteger(checkCode) {
	if(checkCode===null || checkCode===undefined) {
		return false
	}
	var reg = /^[1-9]\d{0,5}$/;
	return reg.test(checkCode);
}
function checkNum(data) {
	if(data===null || data===undefined) {
		return false
	}
	var reg = /^[1-9]\d*$/;
	return reg.test(data);
}
function checkNum4(data) {
	if(data===null || data===undefined) {
		return false
	}
	var reg = /^[1-9]\d{0,3}$/;
	return reg.test(data);
}
///未完成
function checkBigDicemal(checkCode) {
	if(checkCode===null || checkCode===undefined) {
		return false
	}
	var reg = /^((0\.(\d|\d{2}))|([1-9]\d{0,9})|([1-9]\d{0,9}\.(\d|\d{2}))|(0))$/;
	return reg.test(checkCode);
}