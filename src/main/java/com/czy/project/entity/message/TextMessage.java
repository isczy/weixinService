package com.czy.project.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ******************************************************************
 * @brief      text文本消息类
 * @version    0.1
 * @date       2019年12月3日 下午5:07:49
 * @author     ChangZiYang
 *******************************************************************
 */
@XStreamAlias("xml")//指定根节点为<xml></xml>
public class TextMessage extends BaseMessage{

	@XStreamAlias("Content")//指定子节点，相当于起别名
	private String content;
	
	public  TextMessage(Map<String, String> requestMap,String content) {
		super(requestMap);
		//设置msgtype固定为text-文本消息
		this.setMsgType("text");
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TextMessage [content=" + content + ", getToUserName()=" + getToUserName() + ", getFromUserName()="
				+ getFromUserName() + ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType() + "]";
	}


	
}
