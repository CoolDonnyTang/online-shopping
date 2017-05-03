package com.cduestc.tyr.online_shopping.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class IpUtils {
	public static String getAddrByIp(String ip) {
		String queryAddr = "http://www.baidu.com/s?wd="+ip+"&rsv_spt=1&rsv_iqid=0xd24dae4d0000e5b4&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&rsv_t=46f6HZkZhDOe30ei7j5t78PWpNWwqsUlUiRpOcVFvAO%2FNu7lavJEV%2BnPiLKNLEQuA7TY&oq=192.168.1.1&rsv_pq=dd36003c0001395e&prefixsug=192.168.1.1&rsp=0";
		String result = null;
		try {
			URL url = new URL(queryAddr);
			//打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//获取读取流
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			//读取数据
			String line = null;
			while((line=br.readLine()) != null) {	        	
				result = RegUtil.getString(line,"IP地址:&nbsp;"+ip+"</span>(.+)");
				if(result !=null && !result.equals("")) {
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
