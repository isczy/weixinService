package com.czy.project.manager;


import org.junit.jupiter.api.Test;

import com.czy.project.common.log.Log;
import com.czy.project.common.utils.PublicUtil;
import com.czy.project.common.utils.WXUtil;

/**
 * ******************************************************************
 * @brief      设置模板消息
 * @version    0.1
 * @date       2019年12月10日 上午11:35:39
 * @author     ChangZiYang
 *******************************************************************
 */
public class TemplateMessageManager {
		private Log log = new Log(TemplateMessageManager.class);
	/**
	 * ************************************************
	 * 功能描述：设置行业
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月10日】
	 * @author modify:
	 */
	@Test
	public void set() {
		String accessToken = WXUtil.getAccessToken();
		String url = PublicUtil.getPropertiesValue("weixin", "TEMPLATE_SET_INDUSTRY");
		url = url.replace("ACCESS_TOKEN", accessToken);
		String data = "{\r\n" + 
				"    \"industry_id1\":\"1\",\r\n" + 
				"    \"industry_id2\":\"4\"\r\n" + 
				"}";
		String result = PublicUtil.post(url, data);
		log.info("设置行业成功>>"+result);
	}
	
	/**
	 * ************************************************
	 * 功能描述：获取行业
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月10日】
	 * @author modify:
	 */
	@Test
	public void get() {
		String accessToken = WXUtil.getAccessToken();
		String url = PublicUtil.getPropertiesValue("weixin", "TEMPLATE_GET_INDUSTRY");
		url = url.replace("ACCESS_TOKEN", accessToken);
		String result = PublicUtil.get(url);
		log.info("获取行业成功>>"+result);
	}
}
