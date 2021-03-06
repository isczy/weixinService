package com.czy.project.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.czy.project.common.exception.BaseException;

import net.sf.json.JSONObject;

/**
 * ******************************************************************
 * 
 * @brief 第三方工具类-聚合数据-聊天机器人
 * @version 0.1
 * @date 2019年12月4日 上午10:06:12
 * @author ChangZiYang
 *******************************************************************
 */
public class JuheUtil {

	private static final String NAME = "JuheUtil";
	public static String DEF_CHATSET=PublicUtil.getPropertiesValue("juhe", "DEF_CHATSET");
	public static int DEF_CONN_TIMEOUT=Integer.parseInt(PublicUtil.getPropertiesValue("juhe", "DEF_CONN_TIMEOUT"));
	public static int DEF_READ_TIMEOUT=Integer.parseInt(PublicUtil.getPropertiesValue("juhe", "DEF_READ_TIMEOUT"));
	public static String userAgent=PublicUtil.getPropertiesValue("juhe", "userAgent");
	// 配置您申请的KEY
	public static String APPKEY=PublicUtil.getPropertiesValue("juhe", "APPKEY");

	// 1.问答
	public static String getRequest1(String msg) {
		String responseMessage = null;
		String result = null;
		String url = "http://op.juhe.cn/iRobot/index";// 请求接口地址
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("key", APPKEY);// 您申请到的本接口专用的APPKEY
		params.put("info", msg);// 要发送给机器人的内容，不要超过30个字符
		params.put("dtype", "json");// 返回的数据的格式，json或xml，默认为json
		params.put("loc", "");// 地点，如北京中关村
		params.put("lon", "");// 经度，东经116.234632（小数点后保留6位），需要写为116234632
		params.put("lat", "");// 纬度，北纬40.234632（小数点后保留6位），需要写为40234632
		params.put("userid", "");// 1~32位，此userid针对您自己的每一个用户，用于上下文的关联

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				responseMessage = object.getJSONObject("result").getString("text");
				System.out.println("调用聊天机器人接口成功>>返回的结果为：" + responseMessage);
				return responseMessage;
			} else {
				System.out.println("调用聊天机器人接口失败>>" + object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			throw new BaseException(e,"聊天机器人处理问答消息失败", NAME + "-getRequest1", "");
		}
		return responseMessage;
	}

	// 2.数据类型
	public static void getRequest2() {
		String result = null;
		String url = "http://op.juhe.cn/iRobot/code";// 请求接口地址
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("dtype", "");// 返回的数据格式，json或xml，默认json
		params.put("key", APPKEY);// 您申请本接口的APPKEY，请在应用详细页查询

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			throw new BaseException(e,"聊天机器人处理数据类型消息失败", NAME + "-getRequest1", "");
		}
	}


	/**
	 *
	 * @param strUrl 请求地址
	 * @param params 请求参数
	 * @param method 请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map<String, Object> params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			throw new BaseException(e,"聊天机器人调用第三方接口失败", NAME + "-net", "");
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	@SuppressWarnings("rawtypes")
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				throw new BaseException(e,"聊天机器人转换请求参数失败", NAME + "-urlencode", "");
			}
		}
		return sb.toString();
	}
}
