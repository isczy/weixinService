package com.czy.project.common.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;

import com.czy.project.common.exception.BaseException;
import com.czy.project.entity.AccessToken;
import com.czy.project.entity.message.Article;
import com.czy.project.entity.message.BaseMessage;
import com.czy.project.entity.message.NewsMessage;
import com.czy.project.entity.message.TextMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ******************************************************************
 * 
 * @brief 微信工具类
 * @version 0.1
 * @date 2019年12月3日 上午10:37:10
 * @author ChangZiYang
 *******************************************************************
 */
public class WXUtil {
	
	private static final String NAME="WXUtil";
	private static final String TOKEN = PublicUtil.getPropertiesValue("weixin", "TOKEN");
	private static final String GET_TOKEN_URL=PublicUtil.getPropertiesValue("weixin", "GET_TOKEN_URL");
	private static final String APPID=PublicUtil.getPropertiesValue("weixin", "APPID");
	private static final String APPSECRET=PublicUtil.getPropertiesValue("weixin", "APPSECRET");
	private static AccessToken accessToken;//用于存储token
	
	/**
	 * ************************************************
	 * 功能描述：获取token不对外开放-单例模式
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月4日】
	 * @author modify:
	 */
	private static void getToken() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String tokenStr = PublicUtil.get(url);
		String token = PublicUtil.getJsonValue("access_token", tokenStr);
		String expiresIn = PublicUtil.getJsonValue("expires_in", tokenStr);
		accessToken = new AccessToken(token, expiresIn);//创建token对象并存储；	
	}
	
	/**
	 * ************************************************
	 * 功能描述：获取token对外开放-单例模式
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月4日】
	 * @author modify:
	 */
	public static String getAccessToken() {
		try {
			if (accessToken == null || accessToken.isExpired()) {
				getToken();
			} 
		} catch (Exception e) {
			throw new BaseException(e,"获取token失败", NAME + "-getAccessToken", "");
		}
		return accessToken.getAccessToken();
	}
	
	/**
	 * ************************************************ 
	 * 	功能描述：验证签名
	 * @param tinestamp
	 * @param nonce
	 * @param signature
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 */
	public static boolean check(String timestamp, String nonce, String signature) {

		try {
			// 1）将token、timestamp、nonce三个参数进行字典序排序
			String[] strs = { TOKEN, timestamp, nonce };
			Arrays.sort(strs);
			// 2）将三个参数字符串拼接成一个字符串进行sha1加密
			String str = strs[0] + strs[1] + strs[2];
			String mysig = sha1(str);
			// 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
			if (signature.equals(mysig)) {
				return true;
			} 
		} catch (Exception e) {
			throw new BaseException(e,"验证签名失败", NAME + "-check", "");
		}
		return false;
	}

	/**
	 * ************************************************ 
	 * 	功能描述：进行sha1加密
	 * @param str
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 */
	private static String sha1(String str) {
		StringBuilder sb = new StringBuilder();
		try {
			// 获取一个加密对象
			MessageDigest md = MessageDigest.getInstance("sha1");
			// 加密
			byte[] digest = md.digest(str.getBytes());
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			// 处理加密结果
			for (byte b : digest) {
				sb.append(chars[(b >> 4) & 15]);
				sb.append(chars[b & 15]);
			}
		} catch (Exception e) {
			throw new BaseException(e,"进行sha1加密失败", NAME + "-sha1", "");
		}
		return sb.toString();
	}

	/**
	 * ************************************************ 
	 * 	功能描述：解析xml数据包
	 * @param is
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 * @throws DocumentException
	 */
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			// 读取输入流，获取文档对象
			Document document = reader.read(is);
			// 根据文档对象获取根节点
			Element root = document.getRootElement();
			// 获取根节点的所有子节点
			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			for (Element element : elements) {
				map.put(element.getName(), element.getStringValue());
			}
			is.close();
		} catch (Exception e) {
			throw new BaseException(e,"解析xml数据包失败", NAME + "-parseRequest", "");
		}
		return map;
	}

	/**
	 * ************************************************ 
	 * 	功能描述：用于处理所有事件和消息回复
	 * @param requestMap
	 * @return 返回xml数据包
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 */
	public static String getResponse(Map<String, String> requestMap) {
		BaseMessage msg = null;
		String msgType = requestMap.get("MsgType");
		switch (msgType) {
		case "text":
			msg = dealTextMessage(requestMap);
			break;
		case "image":
			msg = dealImage(requestMap);
			break;
		case "voice":

			break;
		case "video":

			break;
		case "shortvideo":

			break;
		case "location":

			break;
		case "link":

			break;
		case "event":
			msg = dealEvent(requestMap);
			break;
		default:

			break;
		}
		// 把消息对象处理为xml数据包
		if (msg!=null) {
			return XStreamUtil.beanToXml(msg);
		}
		return null;
	}

	
	

	/**
	 * ************************************************ 
	 * 	功能描述：处理文本消息
	 * @param requestMap
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月3日】
	 * @author modify:
	 */
	private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
		TextMessage tm;
		try {
			//用户发来的内容
			String msg = requestMap.get("Content");
			if (msg.equals("美女图片")) {
				List<Article> articles = new ArrayList<Article>();
				articles.add(new Article("点击看大图", "美腿诱惑，点击看详情",
						"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575441495560&di=5fadf8db83feea60b1f25a92a5bb0d5e&imgtype=0&src=http%3A%2F%2Fdesk-fd.zol-img.com.cn%2Ft_s960x600c5%2Fg4%2FM04%2F04%2F03%2FCg-4WVQSY5GIXCJfAAgcMfHEIBEAARXwQHY7j8ACBxJ524.jpg",
						"https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=%C3%C0%C5%AE&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111"));
				NewsMessage nm = new NewsMessage(requestMap, "1", articles);
				return nm;
			}
			//调用聚合数据的聊天机器人
			String responseMessage = JuheUtil.getRequest1(msg);
			tm = new TextMessage(requestMap, responseMessage);
		} catch (Exception e) {
			throw new BaseException(e,"处理文本消息失败", NAME + "-dealTextMessage", "");
		}
		return tm;
	}

	/**
	 * ************************************************
	 * 功能描述：处理图片消息->进行图片识别-识别出图片中的文字
	 * @param requestMap
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月9日】
	 * @author modify:
	 */
	private static BaseMessage dealImage(Map<String, String> requestMap) {
		
		String json = BaiDuAIUtil.baiduOCR(requestMap.get("PicUrl"));//调用百度ai图片识别
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("words_result");
		Iterator<JSONObject> it = jsonArray.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			JSONObject next = (JSONObject) it.next();
			sb.append(next.getString("words"));
		}
		
		return new TextMessage(requestMap, sb.toString());
	}
	
	/**
	 * ************************************************
	 * 功能描述：处理事件推送
	 * @param requestMap
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月9日】
	 * @author modify:
	 */
	private static BaseMessage dealEvent(Map<String, String> requestMap) {
		String event = requestMap.get("Event");
		switch (event) {
		case "CLICK":
			return dealClick(requestMap);
		case "VIEW":
			return dealView(requestMap);
		default:
			break;
		}
		return null;
	}

	/**
	 * ************************************************
	 * 功能描述：处理view类型的按钮菜单
	 * @param requestMap
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月9日】
	 * @author modify:
	 */
	private static BaseMessage dealView(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ************************************************
	 * 功能描述：处理click菜单
	 * @param requestMap
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月9日】
	 * @author modify:
	 */
	private static BaseMessage dealClick(Map<String, String> requestMap) {
		String key = requestMap.get("EventKey");
		switch (key) {
		case "1"://处理第一个一级菜单
			return new TextMessage(requestMap, "你点了一下第一个一级菜单");
		case "32"://处理第三个一级菜单的第二个子菜单
			return new TextMessage(requestMap, "你点了一下第三个一级菜单的第二个子菜单");
		default:
			break;
		}
		return null;
	}
	
	/**
	 * ************************************************
	 * 功能描述：获取带参数二维码的ticket
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月9日】
	 * @author modify:
	 */
	public static String getQrCodeTicket() {
		String url = PublicUtil.getPropertiesValue("weixin", "CREATE_QRCODE");
		url = url.replace("TOKEN", getAccessToken());
		String data = "{\"expire_seconds\": 600, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
		String result = PublicUtil.post(url, data);
		String ticket = JSONObject.fromObject(result).getString("ticket");
		return ticket;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
