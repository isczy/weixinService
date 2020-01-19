package com.czy.project.entity;

import lombok.Data;
/**
 * ******************************************************************
 * @brief      微信AccessToken类
 * @version    0.1
 * @date       2019年12月6日 下午4:22:24
 * @author     ChangZiYang
 *******************************************************************
 */
@Data
public class AccessToken {
	
	private String accessToken;
	private long expireTime;//过期时间
	
	public AccessToken(String accessToken, String expiresIn) {
		super();
		this.accessToken = accessToken;
		expireTime = System.currentTimeMillis()+Integer.parseInt(expiresIn)*1000;
	}
	
	/**
	 * ************************************************
	 * 功能描述：判断当前token是否过期
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月4日】
	 * @author modify:
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}
	
}
