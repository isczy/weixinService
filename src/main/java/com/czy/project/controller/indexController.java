package com.czy.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czy.project.common.utils.WXUtil;

@Controller
@RequestMapping("/wx")
public class indexController {

	/**
	 * ************************************************
	 * 功能描述：用于微信服务器接入
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 */
	@GetMapping("/index")
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		/**
		 * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		 * timestamp	时间戳
		 * nonce	随机数
		 * echostr	随机字符串
		 */
		if (WXUtil.check(timestamp, nonce, signature)) {
			System.out.println("微信服务器>>接入成功");
			//原样返回echostr
			PrintWriter writer = response.getWriter();
			writer.print(echostr);
			writer.flush();
			writer.close();
		}else {
			System.out.println("微信服务器>>接入失败");
		}
	}
	
	/**
	 * ************************************************
	 * 功能描述：用于接收用户消息和推送消息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 */
	@PostMapping("/index")
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ServletInputStream is = request.getInputStream();
		//处理消息和事件推送
		Map<String, String> requestMap = WXUtil.parseRequest(is);
		System.out.println(requestMap);
		//准备回复的数据包
		String xml = WXUtil.getResponse(requestMap);
		PrintWriter writer = response.getWriter();
		writer.print(xml);
		writer.flush();
		writer.close();
	}
	
}
