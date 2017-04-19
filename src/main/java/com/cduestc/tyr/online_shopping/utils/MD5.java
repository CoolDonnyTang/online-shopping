package com.cduestc.tyr.online_shopping.utils;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class MD5 {
	public static String toMD5(String msg) throws Exception {
		//利用MD5对msg加密
		MessageDigest md = MessageDigest.getInstance("MD5");
		//处理对象是字节数组，返回结果也是字节数组
		byte[] output = md.digest(msg.getBytes());
		/*
		 * 将MD5处理后的字节数组使用Base64转换成字符串
		 */
		String result = Base64.encodeBase64String(output);
		return result;
	}
}
