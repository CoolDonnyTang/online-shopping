package com.cduestc.tyr.online_shopping.controller.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.utils.CheckCode;

@Controller
@RequestMapping("/user")
public class LoginController {
	@RequestMapping("/login.action")
	@ResponseBody
	public ResultData checkUser() {
		return null;
	}
	
	@RequestMapping("/codeImage.action")
	public void checkImage(HttpServletResponse response, HttpSession session) throws Exception {
		/*
		 * 绘制图片
		 */
		//创建内存画板对象
		BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		//获取画笔
		Graphics g = image.getGraphics();
		//设置画笔颜色
		g.setColor(new Color(255,255,255));
		//为画板设置背景颜色
		g.fillRect(0, 0, 80, 30);
		//获取5位0-9、a-z的组成字符串
		String number = CheckCode.getChckCode();
		//将验证码绑订到session,用来验证。
		session.setAttribute("number", number);
		//设置画笔颜色位随机
		Random r = new Random();
		g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
		//设置字体大小和格式
		g.setFont(new Font(null,Font.ITALIC,24));
		//绘制字符串(可以每次只画一个字符，画5次，每个字符的位置和大小不同)
		g.drawString(number, 2, 24);
		//绘制一些干扰线
		for(int i=0;i<(r.nextInt(10)+10);i++) {
			//设置画笔颜色
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(80));
		}
		/*
		 * 压缩图片
		 */
		//设置服务器返回数据类型
		response.setContentType("image/jpeg");
		//获得字节输出流
		OutputStream os = response.getOutputStream();
		//压缩图片并输出
		ImageIO.write(image, "jpeg", os);
		//关闭输出流
		os.close();
	}
}
