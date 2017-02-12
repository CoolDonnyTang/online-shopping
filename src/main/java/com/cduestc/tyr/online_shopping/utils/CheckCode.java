package com.cduestc.tyr.online_shopping.utils;

import java.util.Random;

public class CheckCode {
	public static String getChckCode() {
		String str = "qwertyuipasdfghjklzxcvbnm123456789";
		String code = "";
		Random r = new Random();
		for(int i=0;i<5;i++) {
			code += str.charAt(r.nextInt(str.length()));
		}
		return code;
	}
	public static String getChckCode(int size) {
		if(size<=0) {
			return "";
		}
		String str = "qwertyuipasdfghjklzxcvbnm123456789";
		String code = "";
		Random r = new Random();
		for(int i=0; i<size; i++) {
			code += str.charAt(r.nextInt(str.length()));
		}
		return code;
	}
}
