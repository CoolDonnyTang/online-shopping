package com.cduestc.tyr.online_shopping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	public static Integer getIntNumber(String data, String reg) {
		try{
			Matcher mac = Pattern.compile(reg).matcher(data);
			mac.find();
			String result = mac.group(1);
			return Integer.valueOf(result);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getString(String data, String reg) {
		try{
			Matcher mac = Pattern.compile(reg).matcher(data);
			mac.find();
			String result = mac.group(1);
			return result;
		} catch (Exception e) {
			return "";
		}
	}
}
